package com.solvd.course.lawoffice.web.security;

import com.solvd.course.lawoffice.domain.security.UserDetailsImpl;
import com.solvd.course.lawoffice.web.dto.ConsultationDto;
import com.solvd.course.lawoffice.web.dto.ReviewDto;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import java.util.Objects;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object returnObject;

    public CustomMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    public boolean isReviewCreator(ReviewDto reviewDto) {
        Long id = ((UserDetailsImpl) this.getPrincipal()).getId();
        return reviewDto.getClient().getUserId().equals(id);
    }

    public boolean isReviewOwner() {
        Long id = ((UserDetailsImpl) this.getPrincipal()).getId();
        return ((ReviewDto) this.returnObject).getClient().getUserId().equals(id);
    }

    public boolean isConsultationCreator(ConsultationDto consultationDto) {
        Long id = ((UserDetailsImpl) this.getPrincipal()).getId();
        return consultationDto.getLawyer().getUserId().equals(id);
    }

    public boolean isConsultationRelated() {
        Long id = ((UserDetailsImpl) this.getPrincipal()).getId();
        boolean isClientRelated = false;
        if (Objects.nonNull(((ConsultationDto) this.returnObject).getClient())) {
            isClientRelated = ((ConsultationDto) this.returnObject).getClient().getUserId().equals(id);
        }
        return ((ConsultationDto) this.returnObject).getLawyer().getUserId().equals(id) || isClientRelated;
    }

    @Override
    public void setFilterObject(Object filterObject) {

    }

    @Override
    public Object getFilterObject() {
        return null;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    @Override
    public Object getThis() {
        return null;
    }

}
