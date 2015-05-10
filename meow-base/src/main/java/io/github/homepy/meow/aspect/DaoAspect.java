package io.github.homepy.meow.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class DaoAspect {

	private static final Logger logger = LoggerFactory.getLogger(DaoAspect.class);
	
	@Pointcut("within(io.github.homepy.meow.*.dao.*)")
	public void inDaoLayer() {
	}


	@Around("inDaoLayer()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		Long timeStart = System.currentTimeMillis();
		try {
			return pjp.proceed();
		} catch (Exception e) {
			logger.error("Dao Exception", e);
			throw e;
		} finally {
			Long timeEnd = System.currentTimeMillis();
			Long timeCost = timeEnd - timeStart;
			Signature sig = pjp.getSignature();
			logger.info("{} {}.{} :timeCost ={}", sig.getDeclaringTypeName(), sig.getName(), pjp.getTarget(), timeCost);
		}
	}

}
