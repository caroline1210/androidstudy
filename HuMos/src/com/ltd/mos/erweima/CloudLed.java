package com.ltd.mos.erweima;

import android.hardware.Camera;
/**
 * 
 * @author lidynast
 *
 */
public class CloudLed {
	boolean m_isOn;
	Camera m_Camera;

	public boolean getIsOn() {
		return m_isOn;
	}

	public CloudLed() {
		m_isOn = false;
	}

	public void turnOn() {
		if (!m_isOn) {
			m_isOn = true;
			try {
				m_Camera = Camera.open();
				Camera.Parameters mParameters;
				mParameters = m_Camera.getParameters();
				mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
				m_Camera.setParameters(mParameters);
			} catch (Exception ex) {
			}
		}
	}

	public void turnOff() {
		if (m_isOn) {
			m_isOn = false;
			try {
				Camera.Parameters mParameters;
				mParameters = m_Camera.getParameters();
				mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
				m_Camera.setParameters(mParameters);
				m_Camera.release();
			} catch (Exception ex) {
			}
		}
	}
}
