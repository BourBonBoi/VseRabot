package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.Vacancy

class VacanciesAdapter(private var vacancies: List<Vacancy>) : RecyclerView.Adapter<VacanciesAdapter.VacancyViewHolder>() {

    class VacancyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val companyNameTextView: TextView = itemView.findViewById(R.id.textViewCompanyName)
        val emailTextView: TextView = itemView.findViewById(R.id.textViewEmail)
        val phoneTextView: TextView = itemView.findViewById(R.id.textViewPhone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vacancy, parent, false)
        return VacancyViewHolder(view)
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val vacancy = vacancies[position]
        holder.companyNameTextView.text = vacancy.details.company.name
        holder.emailTextView.text = vacancy.details.company.email


        val phone = vacancy.details.contactList
            .firstOrNull { it.contactType == "Телефон" }
            ?.contactValue ?: "N/A"

        holder.phoneTextView.text = phone
    }

    override fun getItemCount() = vacancies.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateVacancies(newVacancies: List<Vacancy>) {
        vacancies = newVacancies
        notifyDataSetChanged()
    }
}