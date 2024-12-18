package com.example.customlistview

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class MyAlertDialog:DialogFragment() {
    private var removable:Removable? = null
    private var updateble:Updateble? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        removable = context as Removable?
        updateble = context as Updateble?
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val shop = requireArguments().getSerializable("shop")
        val builder = AlertDialog.Builder(
            requireActivity()
        )
        return builder
            .setTitle("Внимание!")
            .setMessage("Преполагаемые действия")
            .setPositiveButton("Удалить"){dialog, which ->
                removable?.remove(shop as Shop)

            }
            .setNeutralButton("Редактировать"){dialog, which ->
                updateble?.update(shop as Shop)
            }
            .setNegativeButton("Отмена", null)
            .create()
    }
}