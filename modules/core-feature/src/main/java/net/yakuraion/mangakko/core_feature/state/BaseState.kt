package net.yakuraion.mangakko.core_feature.state

interface BaseState<SELF : BaseState<SELF, OWNER>, OWNER : BaseOwner<OWNER, SELF>> {

    /**
     * Владелец состояния
     */
    var owner: OWNER?

    /**
     * Получение имени состояния
     *
     * @return имя состояния
     */
    fun getName(): String

    /**
     * Вход в состояние
     */
    fun onEnter()

    /**
     * На выходе из состояния
     */
    fun onExit()
}
