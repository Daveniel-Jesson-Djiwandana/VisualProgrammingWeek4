package com.example.assignmentweek4.soal2

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignmentweek4.R

enum class Hero(
    val heroName: String, val heroClass: String, val classColor: Color, val rarity: String, val level: Int, val cp: Int, val hp: Int, val maxHp: Int, val mp: Int, val maxMp: Int, val atk: Int, val def: Int, val crit: String, val spd: Int, val skillName: String, val skillDesc: String, val image: Int, val category: String, val inSquad: Boolean = false
) {
    ALISTAIR(
        "Alistair", "High Templar", Color(0xFFFFB300), "MYTHIC", 50, 5600,
        1650, 1800, 650, 800, 380, 540, "22%", 105,
        "Wrath of the Heavens",
        "Calls down a celestial hammer, stunning all enemies for 3 seconds and taunting survivors.",
        R.drawable.paladin,
        "Paladin",
        inSquad = true
    ),
    THERESA(
        "Theresa", "Divine Oracle", Color(0xFF00C853), "MYTHIC", 49, 5480,
        1100, 1200, 2100, 2400, 210, 320, "12%", 98,
        "Hymn of Renewal",
        "Heals all allies over 5 seconds and removes one debuff from each of them.",
        R.drawable.cleric,
        "Cleric"
    ),
    VESPERA(
        "Vespera", "Shadowblade", Color(0xFFD500F9), "MYTHIC", 48, 5350,
        890, 950, 410, 600, 460, 210, "41%", 140,
        "Umbral Strike",
        "Teleports behind the target and deals massive damage with a guaranteed critical hit.",
        R.drawable.rogue,
        "Rogue"
    ),
    ELARIA(
        "Elaria", "Archmage", Color(0xFF00E5FF), "MYTHIC", 48, 5120,
        450, 600, 1850, 2000, 430, 180, "25%", 110,
        "Arcane Cataclysm",
        "Unleashes a storm of arcane energy that hits all enemies in the area.",
        R.drawable.archmage,
        "Archmage",
        inSquad = true
    ),
    KAEL(
        "Kael", "Berserker", Color(0xFFFF5C7A), "LEGENDARY", 45, 4300,
        1400, 1500, 200, 400, 500, 260, "30%", 120,
        "Blood Fury",
        "Increases attack speed and damage as his HP gets lower.",
        R.drawable.berserker,
        "Berserker",
        inSquad = true
    ),
    SERAPHIM(
        "Seraphim", "Holy Knight", Color(0xFFFFB300), "MYTHIC", 43, 4900,
        1500, 1600, 500, 700, 340, 480, "18%", 95,
        "Judgment Radiance",
        "Bathes the battlefield in holy light, shielding allies and burning all enemies.",
        R.drawable.paladin,
        "Paladin"
    ),
    LUCIEN(
        "Lucien", "Dawn Guardian", Color(0xFFFFB300), "MYTHIC", 38, 4000,
        1250, 1350, 350, 500, 300, 410, "15%", 90,
        "Aegis of Dawn",
        "Raises a radiant barrier that absorbs damage for the whole squad for 4 seconds.",
        R.drawable.paladin,
        "Paladin"
    ),
    RAGNAR(
        "Ragnar", "Warlord", Color(0xFFFF5C7A), "LEGENDARY", 44, 4850,
        1550, 1650, 220, 400, 520, 280, "33%", 118,
        "Ragnarok Slash",
        "Cleaves through a line of enemies, dealing bonus damage to those below half HP.",
        R.drawable.berserker,
        "Berserker"
    ),
    MORGATH(
        "Morgath", "Void Sorcerer", Color(0xFF00E5FF), "LEGENDARY", 44, 4900,
        500, 650, 1900, 2100, 450, 190, "27%", 108,
        "Event Horizon",
        "Opens a rift that pulls enemies together and crushes them with void energy.",
        R.drawable.archmage,
        "Archmage"
    ),
    ZEPHYROS(
        "Zephyros", "Storm Caller", Color(0xFF00E5FF), "LEGENDARY", 38, 3950,
        400, 520, 1500, 1700, 380, 150, "20%", 104,
        "Tempest Chain",
        "Summons chain lightning that jumps between up to 5 enemies.",
        R.drawable.archmage,
        "Archmage"
    ),
    NYX(
        "Nyx", "Night Stalker", Color(0xFFD500F9), "MYTHIC", 43, 4800,
        780, 850, 380, 550, 480, 190, "44%", 150,
        "Shadow Requiem",
        "Vanishes into darkness and strikes three times, each hit with increased crit chance.",
        R.drawable.rogue,
        "Rogue"
    ),
    RAVEN(
        "Raven", "Phantom Thief", Color(0xFFD500F9), "MYTHIC", 37, 3900,
        650, 720, 300, 450, 400, 160, "36%", 138,
        "Midnight Heist",
        "Steals a buff from the target and applies it to herself while dodging the next attack.",
        R.drawable.rogue,
        "Rogue"
    ),
    SERAPHINA(
        "Seraphina", "Light Bringer", Color(0xFF00C853), "LEGENDARY", 42, 4750,
        1000, 1100, 2000, 2200, 190, 290, "14%", 96,
        "Radiant Blessing",
        "Restores a large amount of HP to the whole squad and grants a shield for 6 seconds.",
        R.drawable.cleric,
        "Cleric"
    ),
    CELESTINE(
        "Celestine", "Grace Priestess", Color(0xFF00C853), "LEGENDARY", 36, 3850,
        850, 950, 1600, 1800, 160, 240, "10%", 92,
        "Gentle Mercy",
        "Heals the ally with the lowest HP and cleanses all debuffs from them.",
        R.drawable.cleric,
        "Cleric"
    )
}
@Composable
fun Soal2View() {
    var searchText by remember { mutableStateOf("") }
    var filterApa: String by remember { mutableStateOf("All (${Hero.entries.size})") }
    val filters = listOf(
        "All (${Hero.entries.size})", "Berserker (${Hero.entries.count { it.category == "Berserker" }})", "Archmage (${Hero.entries.count { it.category == "Archmage" }})", "Paladin (${Hero.entries.count { it.category == "Paladin" }})", "Rogue (${Hero.entries.count { it.category == "Rogue" }})", "Cleric (${Hero.entries.count { it.category == "Cleric" }})"
    )
    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFF0C0D14)).padding(top = 20.dp)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column {
                        Text(
                            text = "Party Roster",
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Guild Vanguard • 14 Heroes Available",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }

                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(Color(0xFFFFB300).copy(alpha = 0.12f))
                            .border(1.dp, Color(0xFFFFB300), RoundedCornerShape(50))
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "★ Expedition",
                            color = Color(0xFFFFB300),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFF1A1A2E))
                        .border(1.dp, Color(0xFFFFB300), RoundedCornerShape(20.dp))
                        .padding(14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF00E676))
                            )
                            Spacer(Modifier.width(5.dp))
                            Text(text = "Expedition Squad (3/4)", color = Color.White)
                        }
                        Text(
                            text = "⚔\uFE0F 15020 CP",
                            color = Color(0xFFFFB300),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        listOf(
                            R.drawable.berserker to Color(0xFFFF5C7A),
                            R.drawable.archmage to Color(0xFF00E5FF),
                            R.drawable.paladin to Color(0xFFFFB300)
                        ).forEach { (image, borderColor) ->
                            Box(
                                modifier = Modifier
                                    .size(52.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.White)
                                    .border(2.dp, borderColor, RoundedCornerShape(12.dp))
                            ) {
                                Image(
                                    painter = painterResource(id = image),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(2.dp)
                                )
                            }
                            Spacer(Modifier.width(8.dp))
                        }

                        Box(
                            modifier = Modifier
                                .size(52.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFF151527))
                                .border(1.dp, Color(0xFF2A2A45), RoundedCornerShape(12.dp))
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "+",
                                color = Color.Gray,
                                fontSize = 22.sp
                            )
                        }

                        Spacer(Modifier.weight(1f))

                        Text(
                            text = "Deploy \u26A1",
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 14.sp,
                            maxLines = 1,
                            softWrap = false,
                            modifier = Modifier
                                .clip(RoundedCornerShape(14.dp))
                                .background(Color(0xFFFFB300))
                                .clickable { }
                                .padding(horizontal = 14.dp, vertical = 16.dp)
                        )
                    }
                }
            }

            item {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.outline_search_24),
                            contentDescription = "Search",
                            tint = Color.Gray
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Search by hero name, class, or rarity...",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF161826),
                        unfocusedContainerColor = Color(0xFF161826),
                        focusedBorderColor = Color(0xFF5E657E),
                        unfocusedBorderColor = Color(0xFF5E657E),
                        focusedTextColor = Color(0xFF5E657E),
                        unfocusedTextColor = Color(0xFF5E657E),
                        cursorColor = Color(0xFFFFB300)
                    )
                )
            }
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    LazyRow( modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(filters) { labels ->
                            val isSelected = labels == filterApa
                            Text(
                                text = labels,
                                color = if (isSelected) Color.Black else Color.White,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50.dp))
                                    .background(
                                        if (isSelected) Color(0xFFFFB300) else Color(
                                            0xFF25283E
                                        )
                                    )
                                    .clickable { filterApa = labels }
                                    .padding(horizontal = 14.dp, vertical = 8.dp)
                            )
                        }
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Sort: Power",
                        color = Color(0xFFFFB300),
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF1B2440))
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                }
            }
            items(Hero.entries, key = { it.name }) { hero ->
                var expanded by remember { mutableStateOf(false) }
                val shape = RoundedCornerShape(20.dp)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize()
                        .clip(shape)
                        .background(Color(0xFF151527))
                        .border(
                            width = if (hero.inSquad) 2.dp else 1.dp,
                            color = if (hero.inSquad) Color(0xFFFFB300) else Color(0xFF2A2A45),
                            shape = shape
                        )
                        .clickable { expanded = !expanded }
                        .padding(14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text  = hero.rarity,
                            color = if (hero.rarity == "LEGENDARY") Color(0xFFFFB300) else Color(0xFFFF4D4D),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .border(
                                    1.dp,
                                    if (hero.rarity == "LEGENDARY") Color(0xFFFFB300) else Color(0xFFFF4D4D),
                                    RoundedCornerShape(6.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = hero.heroClass,
                            color = hero.classColor,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(hero.classColor.copy(alpha = 0.15f))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = "⚔\uFE0F ${hero.cp} CP",
                            color = Color(0xFFFFB300),
                            fontWeight = FontWeight.ExtraBold
                        )
                        if (hero.inSquad) {
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = "IN SQUAD",
                                color = Color(0xFFFFB300),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .border(1.dp, Color(0xFFFFB300), CircleShape)
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }
                    Spacer(Modifier.height(10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(76.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(Color.White)
                                .border(2.dp, hero.classColor, RoundedCornerShape(14.dp))
                        ) {
                            Image(
                                painter = painterResource(id = hero.image),
                                contentDescription = hero.name,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(4.dp)
                            )
                            Text(
                                text = "Lvl ${hero.level}",
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 2.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color.Black.copy(alpha = 0.75f))
                                    .padding(horizontal = 6.dp, vertical = 1.dp)
                            )
                        }
                        Spacer(Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Text(
                                    text = hero.name,
                                    color = Color.White,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Icon(
                                    painter = painterResource (if(expanded) {R.drawable.outline_keyboard_arrow_up_24}
                                    else {R.drawable.outline_keyboard_arrow_down_24}),
                                    contentDescription = null,
                                    tint = Color.Gray
                                )
                            }
                            Spacer(Modifier.height(6.dp))

                            listOf(
                                Triple("HP", hero.hp to hero.maxHp, Color(0xFFFF4D4D)),
                                Triple("MP", hero.mp to hero.maxMp, Color(0xFF1E88E5))
                            ).forEach { (label, value, barColor) ->
                                val (current, max) = value
                                Row(
                                    modifier = Modifier.padding(vertical = 3.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = label,
                                        color = Color.Gray,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.width(28.dp)
                                    )
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(8.dp)
                                            .clip(CircleShape)
                                            .background(Color(0xFF05050C))
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth(current.toFloat() / max.toFloat())
                                                .fillMaxHeight()
                                                .clip(CircleShape)
                                                .background(barColor)
                                        )
                                    }
                                    Text(
                                        text = "$current / $max",
                                        color = Color(0xFFCFCFE6),
                                        fontSize = 12.sp,
                                        textAlign = TextAlign.End,
                                        modifier = Modifier.width(84.dp)
                                    )
                                }
                                Spacer(Modifier.padding(3.dp))
                            }
                        }
                    }
                    if (expanded){
                        Spacer(Modifier.padding(12.dp))
                        Spacer(Modifier.height(12.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Color(0xFF2A2A45))
                        )
                        Spacer(Modifier.height(12.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf(
                                "ATK" to hero.atk.toString(),
                                "DEF" to hero.def.toString(),
                                "CRIT" to hero.crit,
                                "SPD" to hero.spd.toString()
                            ).forEach { (label, value) ->
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(Color(0xFF25283E))
                                        .padding(vertical = 8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        label,
                                        color = when (label) {
                                            "ATK" -> Color.Red
                                            "DEF" -> Color(0xFF4DB04F)
                                            "CRIT" ->  Color(0xFFFFB300)
                                            "SPD" -> Color(0xFF01E2FF)
                                            else ->  Color(0xFFFFB300)
                                        },
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        value,
                                        color = Color.White,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                }
                            }
                        }
                        Spacer(Modifier.padding(8.dp))

                        Column(
                            modifier = Modifier.fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFF25283E))
                                .padding(12.dp)
                        ){
                            Text(
                                text = "★ ${hero.skillName}",
                                color = Color(0xFFFFB300),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = hero.skillDesc,
                                fontSize = 15.sp,
                                color = Color.White
                            )
                        }
                        Spacer(Modifier.height(10.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf(
                                "🧪 Heal (+250 HP)" to Color(0xFF00C853),
                                "⚔\uFE0F Add to Squad" to Color(0xFFFFB300)
                            ).forEach { (label, color) ->
                                Text(
                                    text = label,
                                    color = color,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(color.copy(alpha = 0.15f))
                                        .border(1.dp, color, RoundedCornerShape(12.dp))
                                        .clickable { }
                                        .padding(vertical = 10.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal2Preview() {
    Soal2View()
}