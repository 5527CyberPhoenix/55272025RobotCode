// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Ports;
import frc.robot.Constants.motorSpeeds;

public class CoralIntake extends SubsystemBase {
  private static SparkMax motor = new SparkMax(Ports.coralMotorCANID, MotorType.kBrushless);
  private static SparkMaxConfig set = new SparkMaxConfig();
  /** Creates a new CoralIntake. */
  private static final boolean motorInverted = true;
  public CoralIntake() {
    set 
        .inverted(motorInverted)
        .idleMode(IdleMode.kBrake);
}
  public void run(){
     motor.set(motorSpeeds.intakespeed);
 }
   public void spit(){
     motor.set(-motorSpeeds.intakespeed);
 }
   public void STOP(){
     motor.stopMotor();
     }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
