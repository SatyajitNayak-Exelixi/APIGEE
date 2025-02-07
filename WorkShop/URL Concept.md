# :label::bookmark: URL vs URI :high_brightness:

## What is a URL?
**URL (Uniform Resource Locator)** is a specific type of URI that tells where a resource is located and how to access it.

### Example (Facebook):
```
https://www.facebook.com/profile.php?id=12345
```
- **Scheme**: https
- **Domain**: www.facebook.com
- **Path**: /profile.php
- **Query**: id=12345 (identifies a user profile)

---

## What is a URI?
**URI (Uniform Resource Identifier)** is a general term that includes URLs and URNs. It identifies a resource by location, name, or both.

### Example:
- **URL**: `https://www.facebook.com/profile.php?id=12345` (location-based)
- **URN**: `urn:isbn:978-3-16-148410-0` (name-based, no location)

---

## Key Differences

| **Feature**        | **URL**                                | **URI**                           |
|--------------------|-------------------------------------|-----------------------------------|
| **Purpose**       | Specifies location & access method | Identifies a ely  |
| **Type**          | Subset of URI                        | Superset (resource uniquincludes URLs & URNs)  |
| **Includes**      | Protocol, domain, path, etc.       | URLs & URNs                      |
| **Example**       | `https://www.facebook.com/home.php` | `urn:isbn:978-3-16-148410-0`      |

---

## Quick Summary
- **URL**: Tells *where* and *how* to access a resource.
- **URI**: Identifies a resource by name, location, or both.

---

## Visual Representation
```
URI ──> URL: https://www.facebook.com/home.php
     └── URN: urn:isbn:978-3-16-148410-0
```
