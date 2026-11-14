package com.example.eightbp.geometry

import kotlin.math.*

data class Vec2(val x: Double, val y: Double) {
    operator fun plus(o: Vec2) = Vec2(x + o.x, y + o.y)
    operator fun minus(o: Vec2) = Vec2(x - o.x, y - o.y)
    operator fun times(k: Double) = Vec2(x * k, y * k)
    fun length() = hypot(x, y)
    fun normalized(): Vec2 {
        val len = length()
        require(len > 1e-9) { "Cannot normalize a zero-length vector" }
        return this * (1.0 / len)
    }
    fun dot(o: Vec2) = x * o.x + y * o.y
}

data class AimResult(val direction: Vec2, val contactPoint: Vec2, val degrees: Double)

/** Basic equal-mass, idealized 2D billiards geometry; ignores spin and cloth effects. */
object BilliardsGeometry {
    fun aim(cue: Vec2, target: Vec2, pocket: Vec2, ballRadius: Double): AimResult {
        require(ballRadius > 0)
        val targetToPocket = (pocket - target).normalized()
        val contact = target - targetToPocket * (2.0 * ballRadius)
        val direction = (contact - cue).normalized()
        val degrees = Math.toDegrees(atan2(direction.y, direction.x))
        return AimResult(direction, contact, degrees)
    }

    /** Reflect a direction from a horizontal or vertical cushion normal. */
    fun reflect(direction: Vec2, normal: Vec2): Vec2 {
        val n = normal.normalized()
        return (direction - n * (2.0 * direction.dot(n))).normalized()
    }

    fun estimatePower(distance: Double, maxDistance: Double): Double {
        require(distance >= 0 && maxDistance > 0)
        return (distance / maxDistance).coerceIn(0.05, 1.0)
    }
}
