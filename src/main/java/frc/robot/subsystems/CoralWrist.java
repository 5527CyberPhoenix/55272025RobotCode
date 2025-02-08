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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  private boolean atSetpoint = false;
  /** Creates a new CoralWrist. */
  public CoralWrist() {
    
    // Sets the setpoint in raw position mode
  
    }
      //get encoder position 
      public double getCurrentPosition(){
        return encoder.getPosition()*360 - Parameter.wristOffset;
      }
      
      public void setOffset(){
        config.zeroOffset(-180);
      }
     

      //allows user to input setpoints
      public void setMotor(double setpoint){
        boolean atSetpoint = false;
        double tolerance = 3;
        double outputSpeed = .3;
        if(getCurrentPosition() > setpoint + tolerance){
          motor.set(-outputSpeed);
        }
        else if(getCurrentPosition() < setpoint + tolerance){
          motor.set(outputSpeed);
        }
        else{
          motorSTOP();
          atSetpoint = true;
        }
        

      }
      public boolean getGoal(){
        return atSetpoint;
      }
      
        public void motorSTOP(){
          motor.set(0);
          
        }
          public void isReversed(){
            config.inverted(false);
          }

        
      
    // This method will be called once per scheduler run
  @Override
  public void periodic() {
    super.periodic();
    SmartDashboard.putNumber("Absolute Encoder", getCurrentPosition());
    
  }
}
