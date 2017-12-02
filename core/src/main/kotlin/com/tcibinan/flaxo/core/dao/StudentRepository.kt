package com.tcibinan.flaxo.core.dao

import com.tcibinan.flaxo.core.model.StudentEntity
import org.springframework.data.repository.CrudRepository

interface StudentRepository : CrudRepository<StudentEntity, Long> {
}