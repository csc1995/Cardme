package com.popalay.cardme.data

import com.popalay.cardme.data.model.DataCard
import com.popalay.cardme.data.model.DataHolder
import com.popalay.cardme.domain.model.Card
import com.popalay.cardme.domain.model.Holder

fun Card.applyMapper(
) = DataCard(number, title, redactedNumber, cardType, generatedBackgroundSeed, position, isTrash, isPending, holderName)

fun List<Card>.applyMapper() = this.map(Card::applyMapper)

fun Holder.applyMapper(
) = DataHolder(name, isTrash, isPending)