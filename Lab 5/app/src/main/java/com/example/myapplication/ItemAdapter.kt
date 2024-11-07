class ItemAdapter(private val itemList: List<ItemModel>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemText: TextView = itemView.findViewById(R.id.item_text)
        val itemSwitch: Switch = itemView.findViewById(R.id.item_switch)
        val itemLayout: LinearLayout = itemView.findViewById(R.id.item_layout)

        fun bind(item: ItemModel) {
            itemText.text = if (item.isOn) "ON" else "OFF"
            itemSwitch.isChecked = item.isOn
            itemLayout.setBackgroundColor(if (item.isOn) Color.GREEN else Color.RED)

            itemSwitch.setOnCheckedChangeListener { _, isChecked ->
                item.isOn = isChecked
                itemText.text = if (isChecked) "ON" else "OFF"
                itemLayout.setBackgroundColor(if (isChecked) Color.GREEN else Color.RED)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}
