package ipvc.estg.commovtp1.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notas_table")

class nota (


    @PrimaryKey(autoGenerate = true) val id: Int? =null,
    @ColumnInfo(name = "titulo") val titulo: String,
    @ColumnInfo(name = "descricao") val descricao: String

)



