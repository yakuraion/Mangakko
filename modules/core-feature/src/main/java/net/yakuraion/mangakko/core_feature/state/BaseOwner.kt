package net.yakuraion.mangakko.core_feature.state

/**
 * Владелец, внутри которого идёт переключение состояний
 */
interface BaseOwner<SELF : BaseOwner<SELF, STATE>, STATE : BaseState<STATE, SELF>> {

    /**
     * Установка состояния
     *
     * @param state состояние
     */
    fun setState(state: STATE)
}
