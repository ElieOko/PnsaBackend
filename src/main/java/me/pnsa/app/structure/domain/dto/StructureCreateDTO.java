package me.pnsa.app.structure.domain.dto;

import me.pnsa.app.address.domain.dto.*;

import java.time.LocalDateTime;
import java.util.List;

public record StructureCreateDTO(
        Long userId,
        Long structureTypeId,
        Long provinceId,
        String name,
        String description,
        List<ServiceSelectDTO> services,
        List<ResponsibilityDTO> responsibilities,
        List<AddressReadDTO> address,
        List<PhoneNumberReadDTO> phoneNumbers,
        List<EmailReadDTO> emails,
        List<AgendaDTO> agenda,
        Double latitude,
        Double longitude
        ) {
}
