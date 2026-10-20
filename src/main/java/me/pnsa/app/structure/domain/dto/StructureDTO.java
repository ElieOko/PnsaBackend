package me.pnsa.app.structure.domain.dto;

import me.pnsa.app.address.domain.dto.*;
import me.pnsa.app.address.domain.models.Province;
import me.pnsa.app.structure.domain.models.Responsibility;
import me.pnsa.app.structure.domain.models.Service;
import me.pnsa.app.structure.domain.models.StructureType;
import me.pnsa.app.users.domain.models.User;

import java.time.LocalDateTime;
import java.util.List;

public record StructureDTO(
        Long structureId,
        User user,
        StructureTypeReadDTO structureType,
        ProvinceReadDTO province,
        String name,
        String description,
        List<ServiceReadDTO> service,
        List<ResponsibilityDTO> responsibilities,
        List<AddressReadDTO> address,
        List<PhoneNumberReadDTO> phoneNumbers,
        List<EmailReadDTO> emails,
        List<AgendaReadDTO> agenda,
        Double latitude,
        Double longitude
        ) {
}
