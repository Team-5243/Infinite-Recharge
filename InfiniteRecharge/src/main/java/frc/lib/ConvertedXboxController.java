/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.lib;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * Add your docs here.
 */
public class ConvertedXboxController extends Joystick {

    XboxController m_xboxController;

    public ConvertedXboxController(int port) {
        super(port);
        m_xboxController = new XboxController(port);
    }

    public JoystickButton xButton = new JoystickButton(this, 3);
	public JoystickButton yButton = new JoystickButton(this, 4);
	public JoystickButton aButton = new JoystickButton(this, 1);
	public JoystickButton bButton = new JoystickButton(this, 2);
	public JoystickButton rightBumper = new JoystickButton(this, 6);
	public JoystickButton leftBumper = new JoystickButton(this, 5);
	public JoystickButton startButton = new JoystickButton(this, 8);
	public JoystickButton selectButton = new JoystickButton(this, 7);
	public JoystickButton leftStickButton = new JoystickButton(this, 9);
    public JoystickButton rightStickButton = new JoystickButton(this, 10);
    
    public int getDpadValue() {
        int dpadValue = this.getPOV();
        return dpadValue;
    }

    public boolean isDpadUp() {
        return getDpadValue() >= 45 && getDpadValue() <= 135;
    }

    public boolean isDpadDown() {
        return getDpadValue() <= 315 && getDpadValue() >= 225;
    }

    public boolean isDpadLeft() {
        return getDpadValue() > 135 && getDpadValue() < 225;
    }

    public boolean isDpadRight() {
        return (getDpadValue() <= 360 && getDpadValue() > 315) || (getDpadValue() >= 0 && getDpadValue() < 45);
    }

    public XboxController getXboxController() {
        return m_xboxController;
    }
}
