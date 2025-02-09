// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkMaxAlternateEncoder;
import com.revrobotics.spark.SparkRelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.PIDValues;
import frc.robot.Constants.Ports;

public class Elevator extends SubsystemBase {
  private static SparkMax motor1 = new SparkMax(Ports.leftMotorCANID, MotorType.kBrushless);
  private static SparkMax motor2 = new SparkMax(Ports.rightMotorCANID, MotorType.kBrushless);
  //initialize PID controller
  private static SparkClosedLoopController PID = motor1.getClosedLoopController();
  private static SparkMaxConfig set = new SparkMaxConfig();
  private static RelativeEncoder lEncoder = motor1.getAlternateEncoder();
  private static RelativeEncoder rEncoder = motor2.getAlternateEncoder();
  private static final double maxHeightIN = 40;
  private static final double defaultspeed = .5;
  /** Creates a new Elevator. */
  private static final boolean motorInverted = true;
  public Elevator() {
    lEncoder.setPosition(0);
    rEncoder.setPosition(0);
    //set motor parameters
    set
      .follow(Ports.leftMotorCANID, motorInverted)
      .idleMode(IdleMode.kBrake)
      .inverted(motorInverted)
      .encoder
      .positionConversionFactor(Constants.Elevator.revPerInch)
      .velocityConversionFactor(Constants.Elevator.revPerInch);
      
  }
    
    public void motorsSTOP(){
      motor1.set(0);
      motor2.set(0);
   }
    
    

  //set constraints in PID loop
    public void Constraints(){
      set.closedLoop
        .p(PIDValues.kP)
        .i(PIDValues.kI)
        .d(PIDValues.kD)
        .outputRange(.1, .8)
        .velocityFF(1/PIDValues.kV)
        .maxMotion
        .maxAcceleration(5)
        .maxVelocity(10)
        .allowedClosedLoopError(2);
        if(lEncoder.getPosition() >= maxHeightIN && rEncoder.getPosition() >= maxHeightIN){
          motorsSTOP();
        } else{
          motor1.set(defaultspeed);
          motor2.set(defaultspeed);
        }

    }
      public void setPosition(double setpoint){
        PID.setReference(setpoint, ControlType.kPosition);
      }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
