package com.luanvan.learningprogress.services;

import com.luanvan.learningprogress.Repository.KHHTRepository;
import com.luanvan.learningprogress.entity.KeHoachHocTap;
import com.luanvan.learningprogress.model.dto.KeHoachHocTapDTO;
import com.luanvan.learningprogress.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeHoachHocTapService {

    private final KHHTRepository khhtRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public List<KeHoachHocTapDTO> getKeHoachHocTap() {
        return null;
    }
}
