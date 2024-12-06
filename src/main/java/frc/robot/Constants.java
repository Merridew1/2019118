package frc.robot;

/**
 * Constants file.
 */
public final class Constants {
    /**
     * Stick Deadband
     */
    public static final double stickDeadband = 0.1;
    /**
     * Driver ID
     */
    public static final int driverID = 0;
    /**
     * Operator ID
     */
    public static final int operatorID = 1;

    /**
     * Motor CAN id's.
     */
    public static final class Motors {
        public static final class Intake {
            public static int INTAKE_MOTOR_ID = 12;

        }

        public static final class DriveTrain {
            public static int FRONT_LEFT_MOTOR_ID = 2;
            public static int FRONT_RIGHT_MOTOR_ID = 4;
            public static int BACK_RIGHT_MOTOR_ID = 5;
            public static int BACK_LEFT_MOTOR_ID = 3;
        }

        public static final class Hatch {
            public static int HATCH_MOTOR_ID = 10;


        }
    }

    /**
     * Pneumatics CAN id constants.
     */
    public static final class Pneumatics {
    }

    public static final class PID {
        public static final class Hatch {
            public static double P = 0;
            public static double I = 0;
            public static double D = 0;
        }

        public static final class DriveTrain {
            public static double P = 0;
            public static double I = 0;
            public static double D = 0;
        }
    }

}
