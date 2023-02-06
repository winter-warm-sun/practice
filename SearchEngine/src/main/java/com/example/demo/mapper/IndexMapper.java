package com.example.demo.mapper;

import com.example.demo.searcher.DocInfo;
import com.example.demo.searcher.InvertedInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Mapper
public interface IndexMapper {

    void saveForwardIndex(@Param("list") ArrayList<DocInfo> forwardIndex);


    void saveInvertedIndex(@Param("list")ArrayList<InvertedInfo> invertedInfos);

    ArrayList<DocInfo> loadForwardIndex();

    ArrayList<InvertedInfo> loadInvertedIndex();
}
