package com.barab.transportnotes.service.impl;

import com.barab.transportnotes.dto.StatusDto;
import com.barab.transportnotes.entity.TransportStatus;
import com.barab.transportnotes.repository.StatusRepository;
import com.barab.transportnotes.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatusServiceImpl implements StatusService {

    private StatusRepository statusRepository;

    @Override
    public List<StatusDto> findAllItems() {
        List<TransportStatus> statuses = statusRepository.findAll(Sort.by("id"));
        return statuses.stream().map(this::mapToStatusDto).collect(Collectors.toList());
    }

    private StatusDto mapToStatusDto(TransportStatus status) {
        StatusDto statusDto = new StatusDto();
        statusDto.setId(status.getId());
        statusDto.setName(status.getName());
        return statusDto;
    }
}
