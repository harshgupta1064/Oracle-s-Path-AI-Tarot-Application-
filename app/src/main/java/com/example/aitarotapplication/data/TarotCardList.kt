package com.example.aitarotapplication.data
import android.content.Context
import com.example.aitarotapplication.R
import com.example.aitarotapplication.models.tarotCard

fun TarotCardList(context: Context): List<tarotCard> {
    return listOf(
        // Major Arcana (Cards 1-22)
        tarotCard(1, context.getString(R.string.tarot_major_fool)),
        tarotCard(2, context.getString(R.string.tarot_major_magician)),
        tarotCard(3, context.getString(R.string.tarot_major_high_priestess)),
        tarotCard(4, context.getString(R.string.tarot_major_empress)),
        tarotCard(5, context.getString(R.string.tarot_major_emperor)),
        tarotCard(6, context.getString(R.string.tarot_major_hierophant)),
        tarotCard(7, context.getString(R.string.tarot_major_lovers)),
        tarotCard(8, context.getString(R.string.tarot_major_chariot)),
        tarotCard(9, context.getString(R.string.tarot_major_strength)),
        tarotCard(10, context.getString(R.string.tarot_major_hermit)),
        tarotCard(11, context.getString(R.string.tarot_major_wheel_of_fortune)),
        tarotCard(12, context.getString(R.string.tarot_major_justice)),
        tarotCard(13, context.getString(R.string.tarot_major_hanged_man)),
        tarotCard(14, context.getString(R.string.tarot_major_death)),
        tarotCard(15, context.getString(R.string.tarot_major_temperance)),
        tarotCard(16, context.getString(R.string.tarot_major_devil)),
        tarotCard(17, context.getString(R.string.tarot_major_tower)),
        tarotCard(18, context.getString(R.string.tarot_major_star)),
        tarotCard(19, context.getString(R.string.tarot_major_moon)),
        tarotCard(20, context.getString(R.string.tarot_major_sun)),
        tarotCard(21, context.getString(R.string.tarot_major_judgment)),
        tarotCard(22, context.getString(R.string.tarot_major_world)),

        // Minor Arcana: Cups (Cards 23-36, 14 cards)
        tarotCard(23, context.getString(R.string.tarot_cups_ace)),
        tarotCard(24, context.getString(R.string.tarot_cups_two)),
        tarotCard(25, context.getString(R.string.tarot_cups_three)),
        tarotCard(26, context.getString(R.string.tarot_cups_four)),
        tarotCard(27, context.getString(R.string.tarot_cups_five)),
        tarotCard(28, context.getString(R.string.tarot_cups_six)),
        tarotCard(29, context.getString(R.string.tarot_cups_seven)),
        tarotCard(30, context.getString(R.string.tarot_cups_eight)),
        tarotCard(31, context.getString(R.string.tarot_cups_nine)),
        tarotCard(32, context.getString(R.string.tarot_cups_ten)),
        tarotCard(33, context.getString(R.string.tarot_cups_page)),
        tarotCard(34, context.getString(R.string.tarot_cups_knight)),
        tarotCard(35, context.getString(R.string.tarot_cups_queen)),
        tarotCard(36, context.getString(R.string.tarot_cups_king)),

        // Minor Arcana: Pentacles (Cards 37-50, 14 cards)
        tarotCard(37, context.getString(R.string.tarot_pentacles_ace)),
        tarotCard(38, context.getString(R.string.tarot_pentacles_two)),
        tarotCard(39, context.getString(R.string.tarot_pentacles_three)),
        tarotCard(40, context.getString(R.string.tarot_pentacles_four)),
        tarotCard(41, context.getString(R.string.tarot_pentacles_five)),
        tarotCard(42, context.getString(R.string.tarot_pentacles_six)),
        tarotCard(43, context.getString(R.string.tarot_pentacles_seven)),
        tarotCard(44, context.getString(R.string.tarot_pentacles_eight)),
        tarotCard(45, context.getString(R.string.tarot_pentacles_nine)),
        tarotCard(46, context.getString(R.string.tarot_pentacles_ten)),
        tarotCard(47, context.getString(R.string.tarot_pentacles_page)),
        tarotCard(48, context.getString(R.string.tarot_pentacles_knight)),
        tarotCard(49, context.getString(R.string.tarot_pentacles_queen)),
        tarotCard(50, context.getString(R.string.tarot_pentacles_king)),

        // Minor Arcana: Swords (Cards 51-64, 14 cards)
        tarotCard(51, context.getString(R.string.tarot_swords_ace)),
        tarotCard(52, context.getString(R.string.tarot_swords_two)),
        tarotCard(53, context.getString(R.string.tarot_swords_three)),
        tarotCard(54, context.getString(R.string.tarot_swords_four)),
        tarotCard(55, context.getString(R.string.tarot_swords_five)),
        tarotCard(56, context.getString(R.string.tarot_swords_six)),
        tarotCard(57, context.getString(R.string.tarot_swords_seven)),
        tarotCard(58, context.getString(R.string.tarot_swords_eight)),
        tarotCard(59, context.getString(R.string.tarot_swords_nine)),
        tarotCard(60, context.getString(R.string.tarot_swords_ten)),
        tarotCard(61, context.getString(R.string.tarot_swords_page)),
        tarotCard(62, context.getString(R.string.tarot_swords_knight)),
        tarotCard(63, context.getString(R.string.tarot_swords_queen)),
        tarotCard(64, context.getString(R.string.tarot_swords_king)),

        // Minor Arcana: Wands (Cards 65-74, 10 cards)
        tarotCard(65, context.getString(R.string.tarot_wands_ace)),
        tarotCard(66, context.getString(R.string.tarot_wands_two)),
        tarotCard(67, context.getString(R.string.tarot_wands_three)),
        tarotCard(68, context.getString(R.string.tarot_wands_four)),
        tarotCard(69, context.getString(R.string.tarot_wands_five)),
        tarotCard(70, context.getString(R.string.tarot_wands_six)),
        tarotCard(71, context.getString(R.string.tarot_wands_seven)),
        tarotCard(72, context.getString(R.string.tarot_wands_eight)),
        tarotCard(73, context.getString(R.string.tarot_wands_nine)),
        tarotCard(74, context.getString(R.string.tarot_wands_ten))
    )
}
