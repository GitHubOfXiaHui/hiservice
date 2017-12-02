package com.bupt.hiservice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import com.bupt.clientsdk.dto.BaseRequestDTO;

@Aspect
@Component
public class UserValidationImpl {

	@Around("@annotation(com.bupt.hiservice.aspect.UserValidation) && args(req)")
	public void validate(ProceedingJoinPoint pjp, BaseRequestDTO req) throws Throwable {
		String user = req.getUser();
		// String lease = BlockchainAPI.getTransactionDateByHash(user);
		String lease = user;
		if (lease == null) {
			throw new HttpServerErrorException(HttpStatus.FORBIDDEN, "租户不存在！");
		}
		if (isExpired(lease)) {
			throw new HttpServerErrorException(HttpStatus.PAYMENT_REQUIRED, "租期已到，请及时续租！");
		}
		pjp.proceed();
	}

	private boolean isExpired(String lease) {
		long now = System.currentTimeMillis();
		return Long.parseLong(lease) < now;
	}

}
