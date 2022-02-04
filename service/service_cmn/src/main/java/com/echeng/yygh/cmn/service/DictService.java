package com.echeng.yygh.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.echeng.yygh.model.cmn.Dict;
import com.echeng.yygh.model.hosp.HospitalSet;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DictService extends IService<Dict> {
    //根据数据id查询子数据列表
    //@Cacheable(value = "dict",keyGenerator = "keyGenerator")
    List<Dict> findChlidData(Long id);

    List<Dict> findChildData(Long id);

    void exportDictData(HttpServletResponse response);

    //导入数据字典
    @CacheEvict(value = "dict", allEntries=true)
    void importDictData(MultipartFile file);

    //根据dictcode和value查询
    String getDictName(String dictCode, String value);

    //根据dictCode获取下级节点
    List<Dict> findByDictCode(String dictCode);
}
