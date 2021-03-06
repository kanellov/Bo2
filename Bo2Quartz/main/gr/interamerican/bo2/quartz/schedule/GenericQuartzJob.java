package gr.interamerican.bo2.quartz.schedule;

import gr.interamerican.bo2.arch.Operation;
import gr.interamerican.bo2.arch.exceptions.DataException;
import gr.interamerican.bo2.arch.exceptions.LogicException;
import gr.interamerican.bo2.arch.exceptions.UnexpectedException;
import gr.interamerican.bo2.arch.ext.Session;
import gr.interamerican.bo2.arch.utils.ext.Bo2Session;
import gr.interamerican.bo2.impl.open.creation.Factory;
import gr.interamerican.bo2.impl.open.job.JobDescription;
import gr.interamerican.bo2.impl.open.job.JobStatus;
import gr.interamerican.bo2.impl.open.runtime.RuntimeCommand;
import gr.interamerican.bo2.impl.open.utils.Bo2;
import gr.interamerican.bo2.quartz.QuartzConstants;
import gr.interamerican.bo2.quartz.QuartzjobDescription;
import gr.interamerican.bo2.utils.ExceptionUtils;
import gr.interamerican.bo2.utils.ReflectionUtils;
import gr.interamerican.bo2.utils.StringConstants;
import gr.interamerican.bo2.utils.StringUtils;

import java.util.Map;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * creates a Job to be executed through quartz scheduler.
 */
public class GenericQuartzJob implements Job {

	/**
	 * logger.
	 */
	Logger LOGGER = LoggerFactory.getLogger(GenericQuartzJob.class);

	/**
	 * generates the Operation for the given bean.
	 *
	 * @param bean
	 * @return {@link Operation}
	 */
	Operation generateOperationFromBean(JobDescription bean) {
		Operation op = Factory.create(bean.getOperationClass());
		Map<String, Object> parameterMap = bean.getParameters();
		if (parameterMap != null) {
			for (String param : parameterMap.keySet()) {
				Object p = parameterMap.get(param);
				ReflectionUtils.setProperty(param, p, op);
				LOGGER.trace("setting " + param + " with value " + p); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		return op;
	}

	/**
	 * log and re-throw.
	 *
	 * @param e
	 * @param bean
	 * @throws JobExecutionException
	 */
	void logMe(Throwable e, QuartzjobDescription bean) throws JobExecutionException {
		String trace = ExceptionUtils.getThrowableStackTrace(e);
		String msg = "Quartz job " + bean.getJobName() + " failed. " + bean.getJobDescriptionDigest(); //$NON-NLS-1$ //$NON-NLS-2$
		LOGGER.warn(msg);
		LOGGER.error(trace);
		bean.setThrowable(e);
		bean.setExecutionStatus(JobStatus.ERROR);
		throw new JobExecutionException(msg + StringConstants.NEWLINE + e);
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDescription bean = null;
		QuartzjobDescription quartzjobDescription = null;
		try {
			LOGGER.trace("Starting Generic QuartzJob"); //$NON-NLS-1$
			JobDataMap map = context.getJobDetail().getJobDataMap();
			bean = (JobDescription) map.get(QuartzConstants.BEAN_PROP);
			quartzjobDescription = (QuartzjobDescription) map
					.get(QuartzConstants.SCHEDULED_BEAN_PROP);
			Bo2Session.setSession((Session<?, ?>) map.get(QuartzConstants.SESSION_PROP));
			Operation op = generateOperationFromBean(bean);
			RuntimeCommand cmd = null;
			String depl = Bo2.getDefaultDeployment().getDeploymentBean().getPathToSecondaryBatchDeployment();
			if (!StringUtils.isNullOrBlank(depl)) {
				cmd = new RuntimeCommand(depl, op);
			} else {
				cmd = new RuntimeCommand(op);
			}
			LOGGER.debug("Starting " + bean.getJobName() + " with params " + bean.getParameters()); //$NON-NLS-1$ //$NON-NLS-2$
			quartzjobDescription.setExecutionStatus(JobStatus.RUNNING);
			cmd.execute();
			LOGGER.debug("Ended " + bean.getJobName() + " with params " + bean.getParameters()); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (DataException e) {
			logMe(e, quartzjobDescription);
		} catch (LogicException e) {
			logMe(e, quartzjobDescription);
		} catch (UnexpectedException e) {
			logMe(e, quartzjobDescription);
		} catch (RuntimeException e) {
			logMe(e, quartzjobDescription);
		} catch (Error e) {
			logMe(e, quartzjobDescription);
		}
		quartzjobDescription.setExecutionStatus(JobStatus.OK);
	}
}
