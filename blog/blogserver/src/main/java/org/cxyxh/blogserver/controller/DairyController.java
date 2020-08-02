package org.cxyxh.blogserver.controller;

import io.swagger.annotations.*;
import org.cxyxh.blogserver.Interceptor.LoginInterceptor;
import org.cxyxh.blogserver.model.*;
import org.cxyxh.blogserver.service.DiaryService;
import org.cxyxh.blogserver.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.controller
 * @ClassName: DairyController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/16 22:41
 * @Version: 1.0
 */
@RestController
@RequestMapping("/system/diary")
@Api(tags = "日记数据接口")
public class DairyController {

    private final static Logger logger = LoggerFactory.getLogger(DairyController.class);

    @Autowired
    DiaryService diaryService;


    /**
     * 根据页码，每页展示数量，日记对象，创建时间 查询日记
     *
     * @param page            页码
     * @param size            每页展示数量
     * @param diary           日记对象
     * @param createDateScope 创建时间
     * @return
     */
    @ApiOperation(value = "查询日记", notes = "根据页码，每页显示数量，日记对象和创建时间查询日记")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = false),
            @ApiImplicitParam(name = "size", value = "每页展示数量", defaultValue = "10", required = false),
            @ApiImplicitParam(name = "articleComment", value = "日记对象", required = false),
            @ApiImplicitParam(name = "createDateScope", value = "创建时间", required = false)
    })
    @GetMapping("/")
    public RespPageBean getDiaryByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Diary diary, Date[] createDateScope) {
        return diaryService.getDiaryByPage(page, size, diary, createDateScope);
    }


    /**
     * 根据日记ID，删除日记
     *
     * @param idiary 日记ID
     * @return
     */
    @ApiOperation(value = "删除日记", notes = "根据日记ID，删除日记")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idiary", value = "日记ID", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 500, message = "删除失败")
    })
    @DeleteMapping("/{idiary}")
    public RespBean deleteDiaryById(@PathVariable Integer idiary, HttpServletRequest request) {
        User user = UserUtils.getCurrentUser();
        String remark = "";
        if (diaryService.deleteDiaryById(idiary) == 1) {
            remark = "删除日记成功，日记ID[{" + idiary + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.info(remark);
            return RespBean.ok("删除成功");
        } else {
            remark = "删除日记失败，日记ID[{" + idiary + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.error(remark);
            return RespBean.error("删除失败");
        }
    }

    /**
     * 根据日记对象，添加日记
     *
     * @param diary 日记对象
     * @return
     */
    @PostMapping("/")
    @ApiOperation(value = "添加日记", notes = "根据日记对象，添加日记")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "diary", value = "日记对象", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "添加成功"),
            @ApiResponse(code = 500, message = "添加失败")
    })
    public RespBean addDiary(@RequestBody Diary diary, HttpServletRequest request) {
        User user = UserUtils.getCurrentUser();
        String remark = "";
        if (diaryService.addDiary(diary) == 1) {
            remark = "添加日记成功，日记ID[{" + diary.getIdiary() + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.info(remark);
            return RespBean.ok("新增成功");
        } else {
            remark = "删添加日记失败，操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.error(remark);
            return RespBean.error("新增失败");
        }
    }

    /**
     * 根据日记对象，修改日记
     *
     * @param diary 日记对象
     * @return
     */
    @PutMapping("/")
    @ApiOperation(value = "修改日记", notes = "根据日记对象，修改日记")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "diary", value = "日记对象", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功"),
            @ApiResponse(code = 500, message = "修改失败")
    })
    public RespBean updateDiaryById(@RequestBody Diary diary, HttpServletRequest request) {
        User user = UserUtils.getCurrentUser();
        String remark = "";
        if (diaryService.updateDiaryById(diary) == 1) {
            remark = "修改日记成功，日记ID[{" + diary.getIdiary() + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.info(remark);
            return RespBean.ok("修改成功");
        } else {
            remark = "修改日记成功，日记ID[{" + diary.getIdiary() + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.error(remark);
            return RespBean.error("修改失败");
        }
    }

}
