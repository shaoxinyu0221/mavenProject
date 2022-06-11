package com.exam03.web;

import com.alibaba.fastjson.JSON;
import com.exam03.entity.Classes;
import com.exam03.entity.Score;
import com.exam03.entity.Student;
import com.exam03.entity.Subject;
import com.exam03.service.ClassesService;
import com.exam03.service.ServiceProxyFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/exam")
public class StudentServlet extends HttpServlet {

    ClassesService classesService = ServiceProxyFactory.getProxy(new ClassesService());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String opr = request.getParameter("opr");
        if ("getClassList".equals(opr)){
            getClassList(request, response);
        }else if ("gotoAdd".equals(opr)){
            gotoAdd(request, response);
        }else if("addScore".equals(opr)){
            addScore(request, response);
        }else if ("getSubjectList".equals(opr)){
            getSubjectList(request, response);
        }else if ("getStudentList".equals(opr)){
            getStudentList(request, response);
        }else if ("gotoScore".equals(opr)){
            gotoScore(request, response);
        }
    }

    /**跳转到显示成绩页面*/
    private void gotoScore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String classes = request.getParameter("classes");
        if (classes == null || classes.equals("")) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        Integer classId = Integer.parseInt(classes);
        //根据班级id查询学生信息和成绩
        List<Score> studentScoreList = classesService.queryStudentScore(classId);

        //查找语文不及格人数
        Integer notPassChinese = classesService.queryMinChinese(classId);
        request.setAttribute("notPassChinese",notPassChinese);
        //查找数学不及格人数
        Integer notPassMath = classesService.queryMinMath(classId);
        request.setAttribute("notPassMath",notPassMath);
        //查找英语不及格人数
        Integer notPassEnglish = classesService.queryMinEnglish(classId);
        request.setAttribute("notPassEnglish",notPassEnglish);

        //查看语文最低分
        Score minChinese = classesService.selectMinChinese(classId);
        request.setAttribute("minChinese",minChinese);
        //查看语文最高分
        Score maxChinese = classesService.selectMaxChinese(classId);
        request.setAttribute("maxChinese",maxChinese);
        //查看语文平均分
        Double avgChinese = classesService.queryAvgChinese(classId);
        request.setAttribute("avgChinese",avgChinese);


        request.setAttribute("studentList", studentScoreList);

        request.getRequestDispatcher("/student_score.jsp").forward(request, response);

    }


    /**获取学生列表*/
    private void getStudentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String classId = request.getParameter("classId");
        if (classId == null || classId.equals("")){
            return;
        }
        Integer classIdInt = Integer.parseInt(classId);
        List<Student> studentList = classesService.getStudentList(classIdInt);
        String jsonString = JSON.toJSONString(studentList);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(jsonString);
        System.out.println(jsonString);
        out.flush();
        out.close();

    }


    /**获取学科列表*/
    private void getSubjectList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Subject> subList = classesService.getSubjectList();
        String jsonString = JSON.toJSONString(subList);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(jsonString);
        System.out.println(jsonString);
        out.flush();
        out.close();
    }

    /**修改学生成绩*/
    private void addScore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer subjectId = Integer.parseInt(request.getParameter("subject"));
        Integer studentId = Integer.parseInt(request.getParameter("student"));
        Integer score = Integer.parseInt(request.getParameter("score"));
        classesService.addScore(subjectId, studentId, score);
        request.setAttribute("success", "添加成功");
        gotoAdd(request, response);

    }

    private void gotoAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/add_score.jsp").forward(request, response);
    }


    private void getClassList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        List<Classes> claList = classesService.getClaList();

        String jsonString = JSON.toJSONString(claList);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(jsonString);
        System.out.println(jsonString);
        out.flush();
        out.close();



    }
}
