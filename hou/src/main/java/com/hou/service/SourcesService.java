package com.hou.service;

import com.hou.entity.PaginatedSources;
import com.hou.entity.Sources;
import com.hou.mapper.SourcesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SourcesService {
    @Autowired
    private SourcesMapper sourcesMapper;

    public PaginatedSources getSourcesByName(String name, int currentPage) {
        int pageSize=12;
        int offset = (currentPage - 1) * pageSize;
        List<Sources> sources = sourcesMapper.findSourcesByName(name, offset, pageSize);
        long totalCount = sourcesMapper.countSourcesByName(name);
        PaginatedSources paginatedSources = new PaginatedSources(sources, totalCount, currentPage, pageSize);
        return paginatedSources;
    }


    public PaginatedSources getSourcesByBelong(String belong, int page) {
        int pageSize=12;
        int offset = (page - 1) * pageSize;
        List<Sources> sources = sourcesMapper.findSourcesByBelong(belong, offset, pageSize);
        long totalCount = sourcesMapper.countSourcesByBelong(belong);
        PaginatedSources paginatedSources = new PaginatedSources(sources, totalCount, page, pageSize);
        return paginatedSources;
    }

    }




