// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralWrist;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class FlexWrist extends Command {
  CoralWrist wrist; double setpoint; boolean complete = false;
  /** Creates a new FlexWrist. */
  public FlexWrist(CoralWrist Wrist, double setpoint) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.wrist = Wrist;
    this.setpoint = setpoint;
  }
  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(wrist.getCurrentPosition() < 178){
      wrist.setMotor(setpoint);
      complete = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    wrist.motorSTOP();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return wrist.getGoal();

  }
}
