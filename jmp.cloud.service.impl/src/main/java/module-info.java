import com.epam.cloud.service.ServiceImpl;
import com.epm.service.Service;

module jmp.cloud.service.impl {

    provides Service with ServiceImpl;

    requires transitive jmp.service.api;
    requires jmp.dto;

    exports com.epam.cloud.service;
}