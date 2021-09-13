package net.yakuraion.mangakko.core_feature.state

/**
 * @see <a href="https://refactoring.guru/ru/design-patterns/state">Паттерн "Состояние"</a>
 */
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
