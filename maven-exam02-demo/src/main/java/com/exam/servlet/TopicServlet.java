package com.exam.servlet;

import com.exam.entity.Student;
import com.exam.entity.Teacher;
import com.exam.entity.Topic;
import com.exam.exception.MyException;
import com.exam.service.ServiceProxyFactory;
import com.exam.service.StudentService;
import com.exam.service.TeacherService;
import com.exam.service.TopicService;
import com.exam.util.MyBatisUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/topic")
public class TopicServlet extends HttpServlet {
    private TopicService topicProxy = ServiceProxyFactory.getProxy(new TopicService());
    private TeacherService teacherService = ServiceProxyFactory.getProxy(new TeacherService());
    private StudentService studentService = ServiceProxyFactory.getProxy(new StudentService());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String opr = request.getParameter("opr");
        if ("gotoTopicList".equals(opr)){
            gotoTopicList(request, response);
        }else if("queryTeacher".equals(opr)){
            queryTeacher(request, response);
        }else if ("gotoTopicAdd".equals(opr)){
            gotoTopicAdd(request, response);
        } else if ("topicAdd".equals(opr)) {
            topicAdd(request, response);
        }else if("deleteTopic".equals(opr)){
            deleteTopic(request, response);
        }
    }

    /**根据id删除课题*/
    protected void deleteTopic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer id = Integer.parseInt(request.getParameter("deleteId"));
        topicProxy.deleteByid(id);
        request.setAttribute("success", "删除成功");
        gotoTopicList(request, response);
    }

    /**添加课题*/
    protected void topicAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            //获取页面元素
            Integer studentId = Integer.parseInt(request.getParameter("studentId"));
            Integer teacherId = Integer.parseInt(request.getParameter("teacherId"));
            String topicname = request.getParameter("topicname");
            //封装成对象
            Topic topic = new Topic();
            topic.setName(topicname);
            topic.setStudentId(studentId);
            topic.setTeacherId(teacherId);
            //添加
            topicProxy.addTopic(topic,studentId);
            request.setAttribute("success","添加成功");
            //添加成功跳转
            gotoTopicList(request, response);
        } catch (Exception e) {
            if (e instanceof MyException){
                request.setAttribute("error",e.getMessage());
            }else{
                request.setAttribute("error","添加失败");
                e.printStackTrace();
            }
            gotoTopicAdd(request, response);
        }
    }


    /**进入课题添加页面*/
    protected void gotoTopicAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //查询所有学生信息
        List<Student> studentInfo = studentService.queryAllStudent();
        request.setAttribute("studentInfo",studentInfo);
        //查询老师信息
        List<Teacher> teacherInfo = teacherService.queryAllTeacher();
        request.setAttribute("teacherInfo",teacherInfo);
        //跳转详情页
        request.getRequestDispatcher("/topicAdd.jsp").forward(request,response);
    }

    /**查看老师详细信息*/
    protected void queryTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //查询老师信息
        Integer tid = Integer.parseInt(request.getParameter("tid"));
        Teacher teacher = teacherService.queryTeacherById(tid);
        request.setAttribute("teacher",teacher);

        //根据老师id查询相关的课题和学生
        List<Student> studentList = studentService.queryStuByTid(tid);
        request.setAttribute("studentList",studentList);
        //跳转详情页
        request.getRequestDispatcher("/teacherInfo.jsp").forward(request,response);
    }

    /**准备前往话题列表*/
    protected void gotoTopicList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Topic> topicList = topicProxy.queryAllTopic();
        request.setAttribute("topicList",topicList);
        request.getRequestDispatcher("/topicList.jsp").forward(request,response);
    }


}
