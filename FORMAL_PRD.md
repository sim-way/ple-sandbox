# Product Requirements Document: PublicLibrary

## 1. Authorized Roles
*(No explicit roles defined)*

## 2. Domain Entities (Aggregates)
### Aggregate: Book
- **Book**
  - `bookId` (UUID) [Required]
  - `title` (String) [Required]
  - `restricted` (Boolean) [Required]

### Aggregate: Patron
- **Patron**
  - `patronId` (UUID) [Required]
  - `name` (String) [Required]
  - `researcherStatus` (Boolean) [Required]
  - `unpaidFeeBalance` (Decimal) [Required]

### Aggregate: Loan
- **Loan**
  - `loanId` (UUID) [Required]
  - `bookId` (UUID) [Required]
  - `patronId` (UUID) [Required]
  - `checkoutDate` (Date) [Required]
  - `dueDate` (Date) [Required]
  - `returnDate` (Date) 

### Aggregate: Hold
- **Hold**
  - `holdId` (UUID) [Required]
  - `bookId` (UUID) [Required]
  - `patronId` (UUID) [Required]
  - `placedDate` (Date) [Required]
  - `expirationDate` (Date) [Required]
  - `queuePosition` (Integer) [Required]

### Aggregate: Fee
- **Fee**
  - `feeId` (UUID) [Required]
  - `patronId` (UUID) [Required]
  - `loanId` (UUID) [Required]
  - `amount` (Decimal) [Required]
  - `accruedDate` (Date) [Required]
  - `paidAmount` (Decimal) [Required]

## 3. Behaviors and State Transitions
### Book Rules
- **Valid States:** Available, CheckedOut
- **Command: RegisterBook**
  - *Authorized Actors:* Librarian
  - *Transition:* Requires `` → Transitions to `Available`
- **Command: MarkBookAsRestricted**
  - *Authorized Actors:* Librarian
  - *Transition:* Requires `Available` → Transitions to `Available`

### Patron Rules
- **Valid States:** Active, Suspended
- **Command: RegisterPatron**
  - *Authorized Actors:* Librarian
  - *Transition:* Requires `` → Transitions to `Active`
- **Command: SuspendPatron**
  - *Authorized Actors:* System
  - *Transition:* Requires `Active` → Transitions to `Suspended`
- **Command: RestorePatron**
  - *Authorized Actors:* Librarian
  - *Transition:* Requires `Suspended` → Transitions to `Active`

### Loan Rules
- **Valid States:** Active, Overdue, Returned
- **Command: CheckOutBook**
  - *Authorized Actors:* Patron
  - *Transition:* Requires `` → Transitions to `Active`
- **Command: MarkLoanOverdue**
  - *Authorized Actors:* System
  - *Transition:* Requires `Active` → Transitions to `Overdue`
- **Command: ReturnBook**
  - *Authorized Actors:* Librarian
  - *Transition:* Requires `Active` → Transitions to `Returned`
- **Command: ReturnOverdueBook**
  - *Authorized Actors:* Librarian
  - *Transition:* Requires `Overdue` → Transitions to `Returned`

### Hold Rules
- **Valid States:** Pending, ReadyForPickup, Expired, Fulfilled
- **Command: PlaceHold**
  - *Authorized Actors:* Patron
  - *Transition:* Requires `` → Transitions to `Pending`
- **Command: ActivateHold**
  - *Authorized Actors:* System
  - *Transition:* Requires `Pending` → Transitions to `ReadyForPickup`
- **Command: ExpireHold**
  - *Authorized Actors:* System
  - *Transition:* Requires `ReadyForPickup` → Transitions to `Expired`
- **Command: FulfillHold**
  - *Authorized Actors:* Librarian
  - *Transition:* Requires `ReadyForPickup` → Transitions to `Fulfilled`

### Fee Rules
- **Valid States:** Unpaid, PartiallyPaid, Paid
- **Command: AccrueLateFee**
  - *Authorized Actors:* System
  - *Transition:* Requires `` → Transitions to `Unpaid`
- **Command: MakePartialPayment**
  - *Authorized Actors:* Librarian
  - *Transition:* Requires `Unpaid` → Transitions to `PartiallyPaid`
- **Command: PayRemainingBalance**
  - *Authorized Actors:* Librarian
  - *Transition:* Requires `PartiallyPaid` → Transitions to `Paid`

## 4. Domain Events (Consequences)
- **BookCheckedOut** (Emitted by Loan)
- **LoanMarkedOverdue** (Emitted by Loan)
- **BookReturned** (Emitted by Loan)
- **HoldPlaced** (Emitted by Hold)
- **HoldActivated** (Emitted by Hold)
- **HoldExpired** (Emitted by Hold)
- **HoldFulfilled** (Emitted by Hold)
- **LateFeeAccrued** (Emitted by Fee)
- **FeePartiallyPaid** (Emitted by Fee)
- **FeePaid** (Emitted by Fee)
- **PatronSuspended** (Emitted by Patron)
- **PatronRestored** (Emitted by Patron)
- **RestrictedBookMarked** (Emitted by Book)
