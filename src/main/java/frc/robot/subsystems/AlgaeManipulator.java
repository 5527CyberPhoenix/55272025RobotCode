// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Ports;
import frc.robot.Constants.motorSpeeds;

public class AlgaeManipulator extends SubsystemBase {
  private static SparkMax motor1 = new SparkMax(Ports.algaeMotor1CANID, MotorType.kBrushless);
  private static SparkMax motor2 = new SparkMax(Ports.algaeMotor2CANID, MotorType.kBrushless);
  private static SparkMaxConfig set = new SparkMaxConfig();
  private static final boolean motorInverted = true;
  /** Creates a new AlgaeManipulator. */
  public AlgaeManipulator() {
    set
       .inverted(motorInverted)
       .idleMode(IdleMode.kBrake);
        
  }
  public void intake(){
    motor1.set(motorSpeeds.intakespeed);
    motor2.set(motorSpeeds.intakespeed);
  }
    public void spit(){
     motor1.set(-motorSpeeds.intakespeed);
     motor2.set(-motorSpeeds.intakespeed);
    }
      public void STOP(){
        motor1.stopMotor();
        motor2.stopMotor();
      }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
