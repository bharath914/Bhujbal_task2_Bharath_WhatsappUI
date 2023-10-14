package com.bharath.bharath_instagram.data.entity.product

data class Data(
    val count: String = "",
    val group_data: List<GroupData> = emptyList(),
    val group_id: String = "",
    val group_name: String = "",
) {
    fun toExpandableItem(): ExpandableItem {
        return ExpandableItem(
            isExpanded = false,
            groupData = group_data,
            groupId = group_id,
            groupName = group_name,
            itemCount = count
        )
    }
}


data class ExpandableItem(
    var isExpanded: Boolean = false,
    val groupData: List<GroupData> = emptyList(),
    val groupId: String = "",
    val groupName: String = "",
    val itemCount: String = "",
)

