// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
  }
  
  public static class Ports{
    //ELEVATOR CANIDS
    public static final int leftMotorCANID = 1;
    public static final int rightMotorCANID = 2;
     
    //CLIMB CANIDS
    public static final int climbMotor1CANID = 3;
    public static final int climbMotor2CANID = 4;

    //MANIPULATOR CANIDS 
    public static final int algaeMotor1CANID = 5;
    public static final int algaeMotor2CANID = 6;
    public static final int coralMotorCANID = 7;
    public static final int coralWristCANID = 8;
    //1 left 2 right
   
    
  }
    public static class motorSpeeds{
      public static final double intakespeed = .5;
    }
      public static class PIDValues{
        public static final double kP = .1;
        public static final double kI = 0;
        public static final double kD = .01;
        // Velocity for NEO 550
        public static final double kV = 473;


}
  public static class Parameter{
    public static final double wristOffset = 180;
    

  }
    public static class Elevator{
      //TODO calculate these values
      public static double gearRatio = 25;
      public static double diameter = .75;
      public static double revPerInch = (Math.PI *diameter) / gearRatio;
    }
}
