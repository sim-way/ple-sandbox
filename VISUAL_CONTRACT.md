# Domain: Billing

# Domain: Billing

## Domain Purity Score: 100/100

## Architecture Overview

```mermaid
graph TD
    classDef command fill:#2563eb,stroke:#1d4ed8,color:#fff,rx:5px,ry:5px;
    classDef event fill:#16a34a,stroke:#15803d,color:#fff,rx:5px,ry:5px;
    classDef rule fill:#f59e0b,stroke:#b45309,color:#fff,shape:hexagon;
    classDef rejected fill:#dc2626,stroke:#991b1b,color:#fff;
    classDef classBox fill:#4A90E2,stroke:#2E5C8A,stroke-width:2px,color:#fff,font-family:Arial;

    subgraph Subscription [Aggregate: Subscription]
        Ent_Subscription["<b>SubscriptionRoot</b><br/>---<br/>id: UUID<br/>status: String<br/>planType: String<br/>"]:::classBox
    end

```

## Aggregate: Subscription

### Structural View (Class Diagram)

```mermaid
classDiagram
    direction TD
    class SubscriptionRoot {
        UUID id
        String status
        String? planType
    }
```

### Behavioral View (State Diagram)

```mermaid
stateDiagram-v2
    direction TD
    [*] --> Pending
```

### Visual Contract

---

