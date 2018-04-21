package org.flaxo.model.data

import java.util.*
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

/**
 * Student data object.
 */
@Entity(name = "student")
@Table(name = "student")
data class Student(

        @Id
        @GeneratedValue
        override val id: Long = -1,

        val nickname: String = "",

        @ManyToOne(optional = false, fetch = FetchType.LAZY)
        val course: Course = Course(),

        @OneToMany(mappedBy = "student", orphanRemoval = true)
        val solutions: Set<Solution> = emptySet()

) : Identifiable, Viewable {

    override fun view(): Any = let { student ->
        object {
            val name = student.nickname
        }
    }

    override fun toString(): String = "${this::class.simpleName}(id=$id)"

    override fun hashCode() = Objects.hash(id)

    override fun equals(other: Any?): Boolean =
            this::class.isInstance(other)
                    && (other as Identifiable).id == id
}