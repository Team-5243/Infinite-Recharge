package frc.robot.util;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class MappedXboxController {

    private final XboxController controller;

    public final JoystickButton a, b, x, y, rb, lb;

    public MappedXboxController(int port) {
        controller = new XboxController(port);

        a = new JoystickButton(controller, 1);
        b = new JoystickButton(controller, 2);
        x = new JoystickButton(controller, 3);
        y = new JoystickButton(controller, 4);
        rb = new JoystickButton(controller, 6);
        lb = new JoystickButton(controller, 5);
    }

    public double rightX() {
        return rightX(true);
    }

    public double rightX(boolean debouncer) {
        double x = controller.getX(Hand.kRight);
        return Math.abs(x) >= .1 ? x : 0; 
    }

    public double rightY() {
        return rightY(true);
    }

    public double rightY(boolean debouncer) {
        double x = controller.getY(Hand.kRight);
        return Math.abs(x) >= .1 ? x : 0;
    }

    public double leftX() {
        return leftX(true);
    }

    public double leftX(boolean debouncer) {
        double x = controller.getX(Hand.kLeft);
        return Math.abs(x) >= .1 ? x : 0;
    }

    public double leftY() {
        return leftY(true);
    }

    public double leftY(boolean debouncer) {
        double x = controller.getY(Hand.kLeft);
        return Math.abs(x) >= .1 ? x : 0;
    }

    public double rightTrigger() {
        return rightTrigger(true);
    }

    public double rightTrigger(boolean debouncer) {
        double trigger = controller.getTriggerAxis(Hand.kRight);
        return Math.abs(trigger) > .1 ? trigger : 0; 
    }

    public double leftTrigger() {
        return leftTrigger(true);
    }

    public double leftTrigger(boolean debouncer) {
        double trigger = controller.getTriggerAxis(Hand.kRight);
        return Math.abs(trigger) > .1 ? trigger : 0;
    }

    public XboxController getController() {
        return controller;
    }

}