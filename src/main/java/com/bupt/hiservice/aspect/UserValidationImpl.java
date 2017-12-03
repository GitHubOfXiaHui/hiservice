package com.bupt.hiservice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.bupt.clientsdk.dto.BaseRequestDTO;
import com.bupt.clientsdk.dto.BaseResponseDTO;
import com.bupt.clientsdk.dto.enumeration.ResponseEnum;

@Aspect
@Component
public class UserValidationImpl {

	@SuppressWarnings("unchecked")
	@Around("@annotation(com.bupt.hiservice.aspect.UserValidation) && args(req)")
	public Object validate(ProceedingJoinPoint pjp, BaseRequestDTO req) throws Throwable {
		String user = req.getUser();
		// String lease = BlockchainAPI.getTransactionDateByHash(user);
		String lease = user;
		if (lease == null || isExpired(lease)) {
			Signature signature = pjp.getSignature();
			Class<? extends BaseResponseDTO> returnType = ((MethodSignature) signature).getReturnType();
			if (lease == null) {
				return BaseResponseDTO.buildResponse(ResponseEnum.ERROR_11, returnType);
			}
			if (isExpired(lease)) {
				return BaseResponseDTO.buildResponse(ResponseEnum.ERROR_12, returnType);
			}
		}
		return pjp.proceed();
	}

	private boolean isExpired(String lease) {
		long now = System.currentTimeMillis();
		return Long.parseLong(lease) < now;
	}

}
