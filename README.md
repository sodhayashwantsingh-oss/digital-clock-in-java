⏰ Digital Clock (Java)

A real-time Digital Clock application built using Java.
This project displays the current system time and updates automatically every second using Java's Date and Timer classes.

🚀 Features

🕒 Displays real-time system clock

⏱ Updates every second automatically

🎨 Simple and clean GUI

💻 Built using Java Swing

⚡ Lightweight desktop application

🛠 Technologies Used

Java

Swing (javax.swing)

AWT

java.time / Date class

Timer / Thread

📂 Project Structure
DigitalClock/
│
├── DigitalClock.java
└── README.md

💡 How It Works

The program fetches the current system time.

A Timer or Thread updates the clock every 1 second.

The time is displayed inside a JLabel.

Example logic:

Timer timer = new Timer(1000, e -> {
    LocalTime time = LocalTime.now();
    label.setText(time.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
});
timer.start();

▶ How to Run the Project
Using Command Line:

Open terminal

Navigate to project folder

Compile:

javac DigitalClock.java


Run:

java DigitalClock

📌 Requirements

Java JDK 8 or above

Any IDE (Eclipse, NetBeans, IntelliJ) or Command Prompt

📸 UI Components Used

JFrame

JLabel

Timer

Font Styling

📌 Future Enhancements

Add date display

Add 12-hour and 24-hour toggle

Add alarm feature

Add stopwatch/timer

Add custom themes
