# COMSATS University Islamabad, Wah Campus
## Department of Computer Science
### Subject: Software Engineering (BCS-3)
### Assignment 01: Build and Deploy a Small Application (DevOps Integration)

---

**Student Name:** Rehab Nouman  
**Course:** Software Engineering  
**Submission Date:** September 23, 2026  
**File Name:** `RehabNouman_SEAss01`  

---

## 🔗 Required Deliverables & Links

* **GitHub Repository Link:** [https://github.com/rehabnouman/CalculatorApp](https://github.com/rehabnouman/CalculatorApp)  
* **Live Deployed Application Link:** [https://rehabnouman.github.io/CalculatorApp/](https://rehabnouman.github.io/CalculatorApp/)  

---

## 1. Application Name and Purpose

* **Application Name:** Scientific Calculator Web Application  
* **Purpose:** To provide a responsive, user-friendly, and modern scientific calculation tool accessible directly through any web browser. It allows users to perform standard arithmetic operations along with advanced mathematical and trigonometric functions while serving as a practical demonstration of modern DevOps CI/CD practices.

---

## 2. Main Features

1. **Standard Arithmetic Operations:** Addition (`+`), Subtraction (`−`), Multiplication (`×`), Division (`÷`), Percentage (`%`), and Negation (`±`).
2. **Scientific Calculation Functions:** Sine (`sin`), Cosine (`cos`), Tangent (`tan`), and Square Root (`√`).
3. **Dual Display Screen:** Live expression tracking line showing the equation history alongside a bold primary output screen.
4. **Interactive Glassmorphism Dark Theme:** Responsive UI built with custom CSS flex/grid styling, micro-animations, and dynamic font resizing for long input numbers.
5. **Full Keyboard Integration:** Complete physical keyboard support (`0-9`, `+`, `-`, `*`, `/`, `Enter`, `Backspace`, `Escape`).
6. **Error Handling & Guardrails:** Safe evaluation protecting against division by zero and numeric overflow (`Infinity`/`NaN`).

---

## 3. DevOps Flow Followed

```
[Developer Writing Code] 
          │
          ▼
[Git Version Control (HTML, CSS, JS)]
          │
          ▼
[Structured Git Commits (Initial, Feature, Polish)]
          │
          ▼
[GitHub Remote Repository Push]
          │
          ▼
[GitHub Actions CI Pipeline Triggered (.github/workflows/ci.yml)]
          │
          ▼
[Automated Node.js Unit Testing (test.js)]
          │
          ├──────────────────────────┐
          ▼                          ▼
  [CI Success (✔)]            [CI Failure (❌)]
          │                          │
          ▼                          ▼
[Automated Deployment]     [Developer Notified to Fix]
          │
          ▼
[Live GitHub Pages Site (https://rehabnouman.github.io/CalculatorApp/)]
```

---

## 4. Problems Faced and How They Were Solved

| Problem Faced | Root Cause | Solution Applied |
|---|---|---|
| **Git Permission Error (403)** | Windows Credential Manager had cached old credentials for another account. | Used `cmdkey` to clear old cached credentials, updating Git config to `Rehab Nouman` and re-authenticating. |
| **CI Build Failure on Unrelated Dependencies** | Standard JavaFX compiler was missing native GUI dependencies on headless Linux runners. | Migrated to a lightweight, fast Web Stack (HTML5/CSS3/JS + Node.js unit tests) ideal for automated CI and GitHub Pages deployment. |
| **GitHub Pages 404 Error** | Default repository branch was not exposed to Pages. | Configured GitHub Actions `actions-gh-pages` pipeline and published `gh-pages` branch for instant live web hosting. |

---

## 5. What You Learned from Continuous Integration (CI)

1. **Early Bug Detection:** CI tests code automatically on every push, catching bugs immediately before they reach production.
2. **"Works on My Machine" Elimination:** Running automated tests on neutral cloud servers ensures code runs reliably regardless of local developer environments.
3. **Automated Feedback Loop:** Developers get instant visual feedback (Green Checkmark `✔` or Red Cross `❌`) directly on GitHub.
4. **Seamless Deployment:** Integrating CI with CD allows code to be automatically tested and deployed to live hosting (GitHub Pages) without manual intervention.

---

## 📸 Required Screenshots Summary

Below are the key screenshots captured for submission:

1. **Running Application:** Interactive web calculator running locally in browser.
2. **GitHub Repository Files:** File structure displaying `index.html`, `style.css`, `script.js`, `test.js`, and `.github/workflows/ci.yml`.
3. **Commit History:** History displaying structured commits:
   - `a. Initial application structure`
   - `b. Add main feature (JavaScript calculation engine & scientific functions)`
   - `c. Improve design aesthetics and add automated CI unit tests`
   - `Introduce intentional small error for CI failure demonstration`
   - `Fix test assertion error and restore successful CI build pipeline`
4. **Failed CI Workflow:** GitHub Actions run showing failed test step (`❌`).
5. **Successful CI Workflow:** GitHub Actions run showing passed green test step (`✔`).
6. **Deployed Application:** Live website running on GitHub Pages URL.

---

**Best of Luck!**
