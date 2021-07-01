package ru.voodster.weatherstation
abstract class BaseMapper<RealmObject, Model>() {

    abstract fun reverseMap(realmObject: RealmObject?) : Model?

    abstract fun map(model: Model?) : RealmObject?

    fun reverseMap(realmObjectList: List<RealmObject>) : List<Model> {
        val modelList = arrayListOf<Model>()
        realmObjectList.forEach {ro->
            reverseMap(ro)?.let {model->
                modelList.add(model)
            }
        }
        return modelList
    }

    fun map(modelList: List<Model>) : List<RealmObject> {
        val realmObjectList = arrayListOf<RealmObject>()
        modelList.forEach {model->
            map(model)?.let {ro->
                realmObjectList.add(ro)
            }
        }
        return realmObjectList
    }
}