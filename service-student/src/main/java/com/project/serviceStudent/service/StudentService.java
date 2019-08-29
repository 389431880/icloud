package com.project.serviceStudent.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.project.serviceStudent.dao.domain.TEhStudent;
import com.project.serviceStudent.dao.mapper.TEhStudentMapper;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends ServiceImpl<TEhStudentMapper, TEhStudent> {
}
