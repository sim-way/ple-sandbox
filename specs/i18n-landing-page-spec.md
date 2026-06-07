# i18n Landing Page Handoff Spec

**[Context & Goal]**
We are executing the following task: `Landing Page Localization (i18n)`. 
The objective is to implement multi-language support (English, Chinese, French) on the `ple.simway.io` landing page to expand the global GTM funnel, using our new "Zero-Maintenance Code" paradigm messaging.

**[Target Files]**
*(Gaudí: Locate the relevant localization and layout files in the `ple.simway.io` repository. E.g.,)*
- `i18n/en.json` (or equivalent config)
- `i18n/zh.json`
- `i18n/fr.json`
- Landing page layout/component files
- `BOARD.md`
- `Changelog.md`

**[Domain Intent / Pure Spec (MUST BE PROVIDED BY LOBSTER)]**
- **Core Strategy:** The site must detect browser locale but allow manual override via a clean UI toggle.
- **Copy Translations (Hero Section Anchor):**
  - **English (Default):** "Zero-Maintenance Code. Programming languages exist for computers, not humans. The machine must learn human intent, not the other way around."
  - **Chinese (Simplified):** "零维护代码。编程语言是为计算机而生，而非人类。机器必须学习人类的意图，而不是让人类去适应机器。"
  - **French:** "Code Zéro Maintenance. Les langages de programmation existent pour les ordinateurs, pas pour les humains. La machine doit apprendre l'intention humaine, et non l'inverse."

**[Architectural Constraints]**
- The i18n implementation must be SEO-friendly (e.g., using proper lang attributes and sub-path routing like `/zh`, `/fr` if supported by the current frontend framework).
- No hardcoded English strings should remain in the primary Hero or feature sections.

**[Strict Execution Sequence]**
1. **Sync:** Run `git pull origin main` to sync remote state, then create a new feature branch `feat/i18n-localization`.
2. **Implement:** Scaffold the i18n routing and JSON dictionaries in the target files.
3. **Verify:** Run the local dev server and ensure the language switcher successfully toggles the Hero text without breaking hydration or layout.
4. **State Update:** Update `BOARD.md` to move this task to the "In Review" column and update `Changelog.md` with the changes.
5. **Atomic Commit:** Bundle the code, tests, `BOARD.md`, and `Changelog.md` updates into a single commit.
6. **Handoff:** Open a Pull Request against `main` for the structural audit.