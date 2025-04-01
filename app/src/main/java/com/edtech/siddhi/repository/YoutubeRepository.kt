package com.edtech.siddhi.repository

import com.edtech.siddhi.model.DifficultyTag
import com.edtech.siddhi.model.YoutubeVideo
import javax.inject.Inject

class YoutubeRepository @Inject constructor() {

    fun DbmsPlaylist(): List<YoutubeVideo> {
        return listOf(
            YoutubeVideo(
                title = "1. Introduction to DBMS Placements Course 2022",
                link = "https://www.youtube.com/watch?v=eYpXCdvKwEQ",
                channelName = "CodeHelp",
                difficultyTag = DifficultyTag.EASY,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "2. What the heck is Database Management System (DBMS)?",
                link = "https://www.youtube.com/watch?v=TYo_CUnIWP8",
                channelName = "CodeHelp",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "3. DBMS Architecture || Role of DBA",
                link = "https://www.youtube.com/watch?v=mYI2nopkQJE",
                channelName = "CodeHelp",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "4. ER Model Explained || ER Diagram Notations || DBMS for Placements",
                link = "https://www.youtube.com/watch?v=kMHJhhIx5k4",
                channelName = "CodeHelp",
                difficultyTag = DifficultyTag.HARD,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "5. Extended ER Features || Abstraction in ER Model",
                link = "https://www.youtube.com/watch?v=8_dMPX6_qiY",
                channelName = "CodeHelp",
                difficultyTag = DifficultyTag.HARD,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "6. How to think and formulate an ER Diagram || Banking System ER Model",
                link = "https://www.youtube.com/watch?v=w-VfTUvxETQ",
                channelName = "CodeHelp",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "7. Designing the ER Model of FACEBOOK Database || DBMS for Placements",
                link = "https://www.youtube.com/watch?v=sQ1AcVYP18c",
                channelName = "CodeHelp",
                difficultyTag = DifficultyTag.HARD,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "8. Relational Model Explained || DBMS for Placements",
                link = "https://www.youtube.com/watch?v=kUk8PgORTzo",
                channelName = "CodeHelp",
                difficultyTag = DifficultyTag.EASY,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "9. Transform ER Model to Relational Model || ER-Diagram to Tables",
                link = "https://www.youtube.com/watch?v=_xHl2gpoXqI",
                channelName = "CodeHelp",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "10. Complete SQL in 1 VIDEO",
                link = "https://www.youtube.com/watch?v=D_wNQR3LeeM",
                channelName = "CodeHelp",
                difficultyTag = DifficultyTag.HARD,
                isWatched = false,
                isFavorite = false
            )
        )
    }

    fun OsPlaylist(): List<YoutubeVideo> {
        return listOf(
            YoutubeVideo(
                title = "Lecture 1: Introduction to Operating Systems Placements Course 2022",
                link = "https://www.youtube.com/watch?v=_TpOHMCODXo",
                channelName = "CodeHelp",
                difficultyTag = DifficultyTag.EASY,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 2: What is an Operating System?",
                link = "https://www.youtube.com/watch?v=a1l4MceYHaQ",
                channelName = "CodeHelp",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 3: Types of Operating Systems",
                link = "https://www.youtube.com/watch?v=LBqNWOqSzBA",
                channelName = "CodeHelp",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 4: Multi-Tasking vs Multi-Threading",
                link = "https://www.youtube.com/watch?v=iAnEvdCya6M",
                channelName = "CodeHelp",
                difficultyTag = DifficultyTag.HARD,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 5: Components of Operating System",
                link = "https://www.youtube.com/watch?v=kHMXP_i6zew",
                channelName = "CodeHelp",
                difficultyTag = DifficultyTag.HARD,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 6: System Calls in Operating System [Theory + Example + Hands-on Terminal]",
                link = "https://www.youtube.com/watch?v=lo8Z61qCDqQ",
                channelName = "CodeHelp",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 7: How Operating System Boots up?",
                link = "https://www.youtube.com/watch?v=nAr2sLiLDWw",
                channelName = "CodeHelp",
                difficultyTag = DifficultyTag.HARD,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 8: Difference between 32-bit & 64-bit Operating System",
                link = "https://www.youtube.com/watch?v=cE6WoaUnpAM",
                channelName = "CodeHelp",
                difficultyTag = DifficultyTag.EASY,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 9: Comparison between different STORAGES used in Computer",
                link = "https://www.youtube.com/watch?v=KFIStTj2DFw",
                channelName = "CodeHelp",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 10: How OS creates a PROCESS || Introduction to Process",
                link = "https://www.youtube.com/watch?v=ev4PrTlTKzE",
                channelName = "CodeHelp",
                difficultyTag = DifficultyTag.HARD,
                isWatched = false,
                isFavorite = false
            )
        )
    }

    fun cnPlaylist(): List<YoutubeVideo> {
        return listOf(
            YoutubeVideo(
                title = "Lecture 1: What is Computer Network?",
                link = "https://www.youtube.com/watch?v=7NFJGH4PzAs",
                channelName = "KnowledgeGate",
                difficultyTag = DifficultyTag.EASY,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 2: Five components of Data Communication | Computer Networks in Hindi",
                link = "https://www.youtube.com/watch?v=lGsJAbrMVa4",
                channelName = "KnowledgeGate",
                difficultyTag = DifficultyTag.EASY,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 3: Effectiveness of Data Communication in Computer Networks in Hindi",
                link = "https://www.youtube.com/watch?v=B8RHylzFjeA",
                channelName = "KnowledgeGate",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 4: Transmission Mode in Data Communication | Simplex, Half-Duplex and Full-Duplex mode",
                link = "https://www.youtube.com/watch?v=bblYjfCvoJU",
                channelName = "KnowledgeGate",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 5: Type of Connection Point-to-Point and Multi-Point Connection in Computer Networks",
                link = "https://www.youtube.com/watch?v=4jT7TG0NNhc",
                channelName = "KnowledgeGate",
                difficultyTag = DifficultyTag.HARD,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 6: Physical Topology in Computer Networks | Mesh Topology",
                link = "https://www.youtube.com/watch?v=ei5fvr6NHf4",
                channelName = "KnowledgeGate",
                difficultyTag = DifficultyTag.HARD,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 7: Star Topology in Computer Networks in Hindi - types of topology",
                link = "https://www.youtube.com/watch?v=GgV3wUsmi4U",
                channelName = "KnowledgeGate",
                difficultyTag = DifficultyTag.EASY,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 8: Bus Topology in Computer Networks in Hindi - types of topology",
                link = "https://www.youtube.com/watch?v=Q71kgKaUJRY",
                channelName = "KnowledgeGate",
                difficultyTag = DifficultyTag.EASY,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 9: Ring Topology in Computer Networks in Hindi - types of topology",
                link = "https://www.youtube.com/watch?v=xQC6vPlOd_s",
                channelName = "KnowledgeGate",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 10: Basics of Open System Interconnection OSI model in Hindi and how it works",
                link = "https://www.youtube.com/watch?v=9nIhpzMv1BI",
                channelName = "KnowledgeGate",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            )
        )
    }

    fun dsaPlaylist(): List<YoutubeVideo> {
        return listOf(
            YoutubeVideo(
                title = "Lecture 1: Flowchart & Pseudocode + Installation | DSA Series by Shradha Ma'am | C++",
                link = "https://www.youtube.com/watch?v=VTLCoHnyACE",
                channelName = "Apna College",
                difficultyTag = DifficultyTag.EASY,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 2: Variable, Data Types & Operators | DSA Series by Shradha Ma'am | C++",
                link = "https://www.youtube.com/watch?v=Dxu7GKtdbnA",
                channelName = "Apna College",
                difficultyTag = DifficultyTag.EASY,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 3: Conditional Statements & Loops | DSA Series by Shradha Ma'am | C++",
                link = "https://www.youtube.com/watch?v=qR9U6bKxJ7g",
                channelName = "Apna College",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 4: Patterns | DSA Series by Shradha Khapra Ma'am | C++",
                link = "https://www.youtube.com/watch?v=rga_q2N7vU8",
                channelName = "Apna College",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 5: Functions | DSA Series by Shradha Khapra Ma'am | C++",
                link = "https://www.youtube.com/watch?v=P08Z_NC8GuY",
                channelName = "Apna College",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 6: Binary Number System | DSA Series by Shradha Khapra Ma'am | C++",
                link = "https://www.youtube.com/watch?v=xpy5NXiBFvA",
                channelName = "Apna College",
                difficultyTag = DifficultyTag.HARD,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 7: Bitwise Operators, Data Type Modifiers & more | DSA Series by Shradha Khapra Ma'am | C++",
                link = "https://www.youtube.com/watch?v=r-u4uh3QvsQ",
                channelName = "Apna College",
                difficultyTag = DifficultyTag.HARD,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 8: Array Data Structure - Part 1 | DSA Series by Shradha Khapra Ma'am | C++",
                link = "https://www.youtube.com/watch?v=8wmn7k1TTcI",
                channelName = "Apna College",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 9: Vectors in C++ | Arrays Part 2 | DSA Series by Shradha Ma'am",
                link = "https://www.youtube.com/watch?v=NWg38xWYzEg",
                channelName = "Apna College",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Lecture 10: Kadane's Algorithm | Maximum Subarray Sum | DSA Series by Shradha Ma'am",
                link = "https://www.youtube.com/watch?v=9IZYqostl2M",
                channelName = "Apna College",
                difficultyTag = DifficultyTag.HARD,
                isWatched = false,
                isFavorite = false
            )
        )
    }

    fun codesnippetPlaylist(): List<YoutubeVideo> {
        return listOf(
            YoutubeVideo(
                title = "Guess the Output 1 | Tricky C codes for Beginners, Technical Interviews & Gate",
                link = "https://www.youtube.com/watch?v=DWDxKHhEn6g",
                channelName = "Geeks for Geeks",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Guess the Output 2 | Tricky C codes for Beginners, Technical Interviews & Gate",
                link = "https://www.youtube.com/watch?v=2XZCu-IlDx4",
                channelName = "Geeks for Geeks",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Guess the Output 3 | Tricky C codes for Beginners, Technical Interviews & Gate",
                link = "https://www.youtube.com/watch?v=PJIGn34njbA",
                channelName = "Geeks for Geeks",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Guess the Output 4 | Tricky C codes for Beginners, Technical Interviews & Gate",
                link = "https://www.youtube.com/watch?v=YXr9NvvD61s",
                channelName = "Geeks for Geeks",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Guess the Output 5 | Tricky C codes for Beginners, Technical Interviews & Gate",
                link = "https://www.youtube.com/watch?v=e3FoQCtdLOI",
                channelName = "Geeks for Geeks",
                difficultyTag = DifficultyTag.MEDIUM,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Guess the Output 6 | Tricky C codes for Beginners, Technical Interviews & Gate",
                link = "https://www.youtube.com/watch?v=CAGJ8MK28QM",
                channelName = "Geeks for Geeks",
                difficultyTag = DifficultyTag.HARD,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Guess the Output 7 | Tricky C codes for Beginners, Technical Interviews & Gate",
                link = "https://www.youtube.com/watch?v=yjBeHK2pQ14",
                channelName = "Geeks for Geeks",
                difficultyTag = DifficultyTag.HARD,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Guess the Output 8 | Tricky C codes for Beginners, Technical Interviews & Gate",
                link = "https://www.youtube.com/watch?v=9H-XyiPuBFE",
                channelName = "Geeks for Geeks",
                difficultyTag = DifficultyTag.HARD,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Guess the Output 9 | Tricky C codes for Beginners, Technical Interviews & Gate",
                link = "https://www.youtube.com/watch?v=IJjKObUrEtw",
                channelName = "Geeks for Geeks",
                difficultyTag = DifficultyTag.HARD,
                isWatched = false,
                isFavorite = false
            ),
            YoutubeVideo(
                title = "Guess the Output 10 | Tricky C codes for Beginners, Technical Interviews & Gate",
                link = "https://www.youtube.com/watch?v=aIpu8QbkepU",
                channelName = "Geeks for Geeks",
                difficultyTag = DifficultyTag.HARD,
                isWatched = false,
                isFavorite = false
            )
        )
    }

}
