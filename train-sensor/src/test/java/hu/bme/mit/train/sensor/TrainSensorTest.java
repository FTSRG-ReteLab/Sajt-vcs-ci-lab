package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainController controller;
    TrainUser user;

    @Before
    public void before() {
        controller = mock(TrainController.class);
        user = mock(TrainUser.class);


    }

    @Test
    public void TestLowerBoundofAbsoulteMargin() {
        TrainSensor sensor = new TrainSensorImpl(controller, user);
        sensor.overrideSpeedLimit(-1);
        verify(user, times(1)).setAlarmState(true);
    }


    @Test
    public void TestUpperBoundofAbsoulteMargin() {
        TrainSensor sensor = new TrainSensorImpl(controller, user);
        sensor.overrideSpeedLimit(7100);
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void TestInsideBoundofAbsoulteMargin() {
        TrainSensor sensor = new TrainSensorImpl(controller, user);
        sensor.overrideSpeedLimit(100);
        verify(user, times(0)).setAlarmState(true);
    }

    @Test
    public void testRelativeMarginAlarm() {
        TrainSensor sensor = new TrainSensorImpl(controller, user);
        when(controller.getReferenceSpeed()).thenReturn(6666);
        sensor.overrideSpeedLimit(10);
        verify(user, times(1)).setAlarmState(true);

    }
    @Test
    public void testRelativeMarginNotAlarm() {
        TrainSensor sensor = new TrainSensorImpl(controller, user);
        when(controller.getReferenceSpeed()).thenReturn(6666);
        sensor.overrideSpeedLimit(6666);
        verify(user, times(0)).setAlarmState(true);

    }
}
