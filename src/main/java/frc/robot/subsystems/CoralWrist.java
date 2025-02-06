// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.AbsoluteEncoderConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PIDValues;
import frc.robot.Constants.Parameter;
import frc.robot.Constants.Ports;

public class CoralWrist extends SubsystemBase {
  private static SparkMax motor = new SparkMax(Ports.coralWristCANID, MotorType.kBrushless);
  // Initialize closed loop controller
  private static SparkClosedLoopController PIDcontroller = motor.getClosedLoopController();
  private static SparkMaxConfig set = new SparkMaxConfig();
  private static SparkAbsoluteEncoder encoder = motor.getAbsoluteEncoder();
  private static AbsoluteEncoderConfig config = new AbsoluteEncoderConfig();
  /** Creates a new CoralWrist. */
  public CoralWrist(double setpoint) {
    // Sets the setpoint in raw position mode
  PIDcontroller.setReference(setpoint, ControlType.kMAXMotionPositionControl);
    set.closedLoop
      .p(PIDValues.kP)  
      .i(PIDValues.kI)   
      .d(PIDValues.kD)
      .velocityFF(1/PIDValues.kV)
      .outputRange(0.1, 1); 
       
    }
      //get encoder position 
      public double getCurrentPosition(){
        return encoder.getPosition()*360 - Parameter.wristOffset;
      }
      
     

      //sets the motor output speed 
      public void setMotor(double setpoint, double lastError){
        double error = setpoint - getCurrentPosition();
        double errorRate = (error - lastError);
        double outputSpeed = PIDValues.kP * error + PIDValues.kD * errorRate;
        motor.set(outputSpeed);

      }
      
        public void zeroEncoder(){
          config.zeroOffset(0);
          
        }
          public void isReversed(){
            config.inverted(false);
          }
        
      

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
