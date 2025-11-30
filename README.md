
# Keystroke Dynamics Authentication Algorithm 

A behavioral-biometric authentication system. This project demonstrates **Keystroke Dynamics** identifying users based on *how they type*, using timing features like **dwell time** and **flight time**.

The system supports:

- Enrollment (profiling the user's typing rhythm)
- Verification (checking if a new attempt matches the typing profile)
- Real-time keystroke capture
- Feature extraction (dwell/flight times)
- Mean & Standard Deviation modeling
- Threshold-based authentication

---

## 🧠 Architecture Overview

```

Main CLI
│
├── KeystrokeCaptureService  → Captures press/release events
├── FeatureExtractionService → Dwell/flight time extraction
├── EnrollmentService       → Builds UserProfile from N samples
└── VerificationService     → Computes score & checks threshold

```

---

## 🖼 Architecture Diagram

<img width="1270" height="513" alt="image" src="https://github.com/user-attachments/assets/f3410e65-29f6-4826-9889-a7862009b53b" />
