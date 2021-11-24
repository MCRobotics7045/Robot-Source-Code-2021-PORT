THIS IS A PORT OF THE 2020 SOURCE CODE TO THE 2021 VERSION OF WPILIBS. SUCCESSFULL BUILD.

V1

Basic Functional Arcade Drive with Joystick(Y,Z) and Throttle using new 2020 Command-Based Robot architecture.

V2

Added Limelight 2 auto-tracking set to Joystick button 2
Throttle ignored for turn data while tracking, but still applied to move data if driving AND tracking simultaneously.

V3

Intake motor at set spped when joystick 7(Forward/Stop) or Joystick 8 (reverse/stop) toggled.

V4

Indexer motor rotates 1 reveolution (Programmable) when joystick button 3 pressed.

V5

Shooter with Encoder and PID Controller

V6

Added Light Break Sensors to Indexor.
Added Individual Fire Command.
Added TOggle to Indexer for Manual operation.
S1-Shooter
S2-Mid
S3-Start Indexer (when ball drops down)
S4-Intake Counter
      S2
     _____
    /     \
S3 /   O   \
   |   |   | S1
   |   |   |
   |
S4 |


V7

Ported Kat's Color Wheel code into new COmmand Structure format.
Added autorotate command to turn FRC color wheel 4.25 revolutions.
