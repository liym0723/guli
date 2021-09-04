package com.liym.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liym.commonutils.R;
import com.liym.eduservice.entity.EduTeacher;
import com.liym.eduservice.service.EduTeacherService;
import com.liym.eduservice.vo.TeacherQueryVo;
import com.liym.servicebase.exception.MyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-08-28
 */
@Api(value = "讲师管理7")
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    // 查询所有
    @ApiOperation(value = "查询全部")
    @GetMapping("queryAll")
    public R queryAll() {

        if(true){
            throw new MyException(500,"有问题");
        }

        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items", list);
    }

    // 删除 ID 通过URL传输 PathVariable -> 通过URL路径获取 ID
    @ApiOperation("指定ID删除")
    @DeleteMapping("/delete/{id}")
    public R deleteTeach(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        Boolean flag = eduTeacherService.removeById(id);
        return R.err();
    }

    // 分页查询
    @ApiOperation("分页查询")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageList(@PathVariable long current,
                      @PathVariable long limit) {

        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        IPage<EduTeacher> page = eduTeacherService.page(pageTeacher, null);
        return R.ok().data("total", page.getTotal()).data("rows", page.getRecords());
    }

    @ApiOperation("分页条件查询")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageListCondition(@PathVariable long current,
                               @PathVariable long limit,
                               @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        // 1. 创建一个配置对象
        Page<EduTeacher> page = new Page<>(current, limit);
        // 2. 创建检索条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String begin = teacherQueryVo.getBegin();
        String end = teacherQueryVo.getEnd();
        // 多条件组合
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.gt("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }
        // 3. 实现条件查询
        IPage<EduTeacher> pageInfo = eduTeacherService.page(page, wrapper);
        return R.ok().data("total", pageInfo.getTotal()).data("rows", page.getRecords());

    }

    // 增加讲师
    @ApiOperation("新增讲师")
    @PostMapping("addTeach")
    public R addTeach(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        } else {
            return R.err();
        }
    }

    // 修改讲师
    @ApiOperation("修改讲师")
    @PutMapping("updateTeach")
    public R updateTeach(@RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag) {
            return R.ok().message("修改成功");
        } else {
            return R.err().message("修改失败");
        }
    }

}


