# Product Requirements Document: Billing

## 1. Authorized Roles
*(No explicit roles defined)*

## 2. Domain Entities (Aggregates)
### Aggregate: Subscription
- **SubscriptionRoot**
  - `id` (UUID) [Required]
  - `status` (String) [Required]
  - `planType` (String) 

## 3. Behaviors and State Transitions
### Subscription Rules
- **Valid States:** Pending, Active

## 4. Domain Events (Consequences)
